import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Message;
public class Main {

    public static String removeExtraSpaces(String s) {
        return s.replaceAll("\\s{2,}", " ");
    }

    public static void main(String[] args) {
        final String appPassword=  "your app password here";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Frame frame = new Frame("Simple form");
        frame.setSize(new Dimension(900, 660));
        frame.setResizable(false);
        frame.setLayout(null);

        Label heading = new Label("Email Less");
        heading.setForeground(Color.BLACK);
        heading.setBounds(10, 30, 200, 40);
        heading.setFont(new Font("Arial", Font.PLAIN, 30));
        frame.add(heading);
        
        Label emailLabel = new Label("Enter email: ");
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setBounds(10, 80, 200, 40);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        frame.add(emailLabel);
        
        
        TextField email = new TextField();
        email.setBounds(10, 120, 840, 40);
        email.setBackground(Color.WHITE);
        email.setForeground(Color.BLACK);
        email.setFont(new Font("Arial", Font.PLAIN, 22));
        frame.add(email);
        
        Label subjectLabel = new Label("Enter subject Line: ");
        subjectLabel.setForeground(Color.BLACK);
        subjectLabel.setBounds(10, 200, 200, 20);
        subjectLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        frame.add(subjectLabel);

        TextField subjectLine = new TextField();
        subjectLine.setBounds(10, 250, 840, 40);
        subjectLine.setBackground(Color.WHITE);
        subjectLine.setForeground(Color.BLACK);
        subjectLine.setFont(new Font("Arial", Font.PLAIN, 22));
        frame.add(subjectLine);
        
        
        Label composeLabel = new Label("Enter compose: ");
        composeLabel.setBounds(10, 330, 840, 70);
        composeLabel.setBackground(Color.WHITE);
        composeLabel.setForeground(Color.BLACK);
        composeLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        frame.add(composeLabel);


        TextArea compose = new TextArea("", 5, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        compose.setBounds(10, 400, 860, 100); // Fixed width instead of frame.getWidth() - 40
        compose.setBackground(Color.WHITE);
        compose.setForeground(Color.BLACK);
        compose.setFont(new Font("Arial", Font.PLAIN, 22));
        
        frame.add(compose);

        Button submit = new Button("send");
        submit.setBounds(frame.getWidth()/2 - 40, 540, 80, 50);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        
        frame.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = email.getText().trim();
                String subject = removeExtraSpaces( subjectLine.getText().trim());
                String composedString = removeExtraSpaces(compose.getText().trim());
                
                javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication("yashodhar2907@gmail.com", appPassword);
                    }
                }); 
                try {
                    // Create email message
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("yashodhar2907@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(text));
                    message.setSubject(subject);
                    message.setText(composedString);
        
                    // Send email
                    javax.mail.Transport.send(message);
                    System.out.println("Email Sent Successfully!");
        
                } catch (MessagingException x) {
                    x.printStackTrace();
                }
                System.out.println("The entered email was: " + text);
                System.out.println("The entered subject was: " + subject);
                System.out.println("The entered compose was: " + composedString);
            }
        });



        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        
        frame.setVisible(true);
        frame.requestFocus();
        frame.repaint();
    }
}