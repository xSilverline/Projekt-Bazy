import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton
{
    Color color = new Color(0x6F2D31);
    MenuButton(String text)
    {
        setText(text);
        setFocusPainted(false);
        setFont(this.getFont().deriveFont(30f));
        setBackground(color);
        setForeground(Color.WHITE);
    }
}
