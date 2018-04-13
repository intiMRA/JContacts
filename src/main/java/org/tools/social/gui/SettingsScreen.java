package org.tools.social.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.tools.social.gui.event.ChangeLanguageListener;
import org.tools.social.gui.util.LanguageManager;
import org.tools.social.gui.util.Screen;
import org.tools.social.gui.util.SettingsManager;
import org.tools.social.util.Contact;
import org.tools.social.util.XmlContactImport;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class SettingsScreen implements Screen, ChangeLanguageListener {
    private JPanel rootPanel;
    private JComboBox languageField;
    private JButton openBtn;
    private JTextField dbPathField;

    public SettingsScreen()
    {
        this.openBtn.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                fileChooser.setFileFilter(new FileNameExtensionFilter("Database Files", "db"));

                if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null))
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    dbPathField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
    }

    /**
     * @see Screen
     */

    @Override
    public JPanel getPanel() {
        return this.rootPanel;
    }

    @Override
    public void languageChanged(Locale locale) {

    }

    public void fillSettingsForm()
    {
        try {
            String language = SettingsManager.getInstance().getProperty("language");

            switch (language) {
                case "de": {
                    this.languageField.setSelectedItem("Deutsch");
                    break;
                }
                case "fr": {
                    this.languageField.setSelectedItem("Français");
                    break;
                }
                default: {
                    this.languageField.setSelectedItem("English");
                    break;
                }
            }

            this.dbPathField.setText(SettingsManager.getInstance().getProperty("db_path"));
        } catch (IOException exc) {
            throw new InternalError(exc);
        }
    }

    public String getSelectedLanguage()
    {
        String language = (String) this.languageField.getSelectedItem();

        switch (language)
        {
            case "Deutsch":
            {
                return Locale.GERMAN.toString();
            }
            case "Français":
            {
                return Locale.FRENCH.toString();
            }
            default:
            {
                return Locale.ENGLISH.toString();
            }
        }
    }

    public String getSelectedDatabasePath()
    {
        return this.dbPathField.getText();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(3, 3, new Insets(5, 5, 5, 5), -1, 10));
        languageField = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("English");
        defaultComboBoxModel1.addElement("Deutsch");
        defaultComboBoxModel1.addElement("Français");
        languageField.setModel(defaultComboBoxModel1);
        rootPanel.add(languageField, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        openBtn = new JButton();
        openBtn.setText("Open");
        rootPanel.add(openBtn, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, Font.BOLD, -1, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Language:");
        rootPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, Font.BOLD, -1, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Database:");
        rootPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dbPathField = new JTextField();
        dbPathField.setEditable(false);
        rootPanel.add(dbPathField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}