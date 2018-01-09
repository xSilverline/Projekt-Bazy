import javax.swing.*;

public class MenuButton extends JButton
{
    MenuButton(String text)
    {
        setText(text);
        setFocusPainted(false);
        setFont(this.getFont().deriveFont(30f));
    }
}
