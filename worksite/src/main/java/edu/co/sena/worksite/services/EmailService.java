package edu.co.sena.worksite.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Value("${resend.api.key}")
    private String resendApiKey;

    @Value("${resend.from:Worksite <onboarding@resend.dev>}")
    private String remitente;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Async
    public void enviarCorreoHtml(String destinatario, String asunto, String contenidoHtml) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("from", remitente);
            body.put("to", new String[]{destinatario});
            body.put("subject", asunto);
            body.put("html", contenidoHtml);

            String json = objectMapper.writeValueAsString(body);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.resend.com/emails"))
                    .timeout(Duration.ofSeconds(15))
                    .header("Authorization", "Bearer " + resendApiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                System.out.println(">>> Correo HTML enviado a: " + destinatario);
            } else {
                System.err.println(">>> ERROR enviando correo a " + destinatario
                        + ": HTTP " + response.statusCode() + " - " + response.body());
            }

        } catch (Exception e) {
            System.err.println(">>> ERROR enviando correo a " + destinatario + ": "
                    + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String construirCorreoBienvenida(String nombre) {
        String nombreSeguro = escaparHtml(nombre);
        return "<html>"
                + "<body style=\"font-family: Arial, sans-serif; color: #333;\">"
                + "<p>Hola " + nombreSeguro + ",</p>"
                + "<p>Tu cuenta ha sido creada exitosamente. ¡Ya puedes disfrutar de nuestros servicios!</p>"
                + "<p>Saludos,<br/>El equipo de Worksite</p>"
                + "</body>"
                + "</html>";
    }

    private String escaparHtml(String texto) {
        if (texto == null) {
            return "";
        }
        return texto
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}