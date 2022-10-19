package br.org.serratec.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    /*@Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gabriel.souza60@aluno.senai.br");
        message.setTo(para);
        message.setSubject(assunto);
        message.setText(texto);
        message.setText("Dados do cadastro do usuário:\n" + texto + "\n\n\n\n" + "Serratec Residência-2022");
        javaMailSender.send(message);
    }*/

}
