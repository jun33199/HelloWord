package noTitle;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.html.HTML;

public class CipherGUI {

    public static void main(String[] args) {
        new CipherGUI();
    }

    public CipherGUI() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                try {
                    try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//                    ex.printStackTrace();
//                }

                JFrame cipherGUIFrame = new CipherGUIFrame();
                cipherGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                cipherGUIFrame.pack();
                cipherGUIFrame.setLocationRelativeTo(null);
                cipherGUIFrame.setVisible(true);
            }
        });
    }

    class CipherGUIFrame extends JFrame {

        public boolean decrypt = true;

        public CipherGUIFrame() {
            super("Caesar Cipher GUI");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 600);

            final JTextArea area1 = new JTextArea();
            final JTextArea area2 = new JTextArea();
            final JSpinner myspinner = new JSpinner();
            JPanel mainframe = new JPanel();
            JToggleButton mybutton = new JToggleButton("ENCRYPT");
            mainframe.setLayout(new BoxLayout(mainframe, BoxLayout.Y_AXIS));
            JPanel p1 = new JPanel();
            JPanel p2 = new JPanel();
            JPanel p3 = new JPanel();
            p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
            p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
            p1.setBorder(BorderFactory.createTitledBorder("Cleartext"));
            p2.setBorder(BorderFactory.createTitledBorder("KEY"));
            p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
            p3.setBorder(BorderFactory.createTitledBorder("Ciphertext"));
            p1.add(area1);
            p2.add(myspinner);
            p2.add(mybutton);
            p3.add(area2);
            mainframe.add(p1);
            mainframe.add(p2);
            mainframe.add(p3);
            this.add(mainframe);

            ActionListener togglelistener = new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                    boolean selected = abstractButton.getModel().isSelected();
                    decrypt = selected;
                    System.out.println("Selected (True or False?): " + selected + "\n");
                }
            };
            mybutton.addActionListener(togglelistener);

            //the following chunk of code modifies the component to update when spinner is changed automatically
            JComponent comp = myspinner.getEditor();
            JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
            DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
            formatter.setCommitsOnValidEdit(true);
            myspinner.addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent e) {
                    System.out.println("Key spinner changed to: " + myspinner.getValue());
                    EncryptFilter filter = (EncryptFilter) ((AbstractDocument) area2.getDocument()).getDocumentFilter();
                    filter.setKey(Integer.parseInt(myspinner.getValue().toString()));
                    area2.setText(area1.getText());
                }
            });

            ((AbstractDocument) area2.getDocument()).setDocumentFilter(new EncryptFilter(0));

            area1.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    try {
                        Document doc = e.getDocument();
                        String text = doc.getText(e.getOffset(), e.getLength());
                        Document encryptDoc = area2.getDocument();
                        encryptDoc.insertString(e.getOffset(), text, null);
                    } catch (BadLocationException exp) {
                        exp.printStackTrace();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    try {
                        Document doc = e.getDocument();
                        Document encryptDoc = area2.getDocument();
                        encryptDoc.remove(e.getOffset(), e.getLength());
                    } catch (BadLocationException exp) {
                        exp.printStackTrace();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    System.out.println("Changed");
                }
            });
        }
    }

    public static String decipher(String istring, int key) {
        String decrypted = "";

        for (int i = 0; i < istring.length(); i++) {

            int c = istring.charAt(i);

            if (Character.isUpperCase(c)) {
                c = c - (key % 26);
                if (c < 'A') {
                    c = c + 26;
                }
            } else if (Character.isLowerCase(c)) {
                c = c - (key % 26);
                if (c < 'a') {
                    c = c + 26;
                }
            }

            decrypted += (char) c;
        }

        return (decrypted);
    }

    public static String encipher(String istring, int key) {
        String encrypted = "";
        for (int i = 0; i < istring.length(); i++) {

            int c = istring.charAt(i);

            if (Character.isUpperCase(c)) {
                c = c - (key % 26);
                if (c < 'A') {
                    c = c + 26;
                }
            } else if (Character.isLowerCase(c)) {
                c = c - (key % 26);
                if (c < 'a') {
                    c = c + 26;
                }
            }

            encrypted += (char) c;
        }

        return (encrypted);
    }

    public class EncryptFilter extends DocumentFilter {

        private int key;

        public EncryptFilter(int key) {
            this.key = key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            System.out.println("Insert");
            text = encipher(text, key);
            super.insertString(fb, offset, text, attr);
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            System.out.println("Remove");
            super.remove(fb, offset, length);
        }



        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

            text = encipher(text, key);
            super.replace(fb, offset, length, text, attrs);
        }

    }
}
 
