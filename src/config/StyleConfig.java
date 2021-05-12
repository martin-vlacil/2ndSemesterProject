package config;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class StyleConfig {

	
	private Color buttonColorSavedBackground;
	private Color buttonColorDeleteBackground;
	private Color buttonColorCancelBackground;
	private Color buttonColorDefaultBackground;
	private Color buttonColorSaveForeground;
	private	Color buttonColorDeleteForeground;
	private Color buttonColorCancelForeground;
	private Color buttonDefaultForeground;
	private Color blueColorDefault;
	private Color redColorDefault;
	private Color backGroundDefaultColor;
	private Color panelDefaultColor;
	private Color backgroundTitleDefaultColor;
	private Color textDefaultColor;
	private Color errorMessageColor;
	private Font buttonDefaultFont;
	private Font buttonBiggerFont;
	private Font labelDefaultFont;
	private Font labelTitleFont;
	private Font labelTitleFontBigger;
	private Font textDefaultSize;
	private Font textBiggerSize;
	private Font logSize;
	private Border buttonSaveBorder;
	private Border buttonCancelBorder;
	private Border buttonDeleteBorder;
	private Border buttonDefaultBorder;
	private Border textAreaBorder;
	private Border textFieldDefaultBorder;
	
	public StyleConfig() {
		buttonColorSavedBackground = new Color(86, 197, 104);
		buttonColorDeleteBackground = null;
		buttonColorCancelBackground = new Color(234, 234, 238);
		buttonColorDefaultBackground = null;
		buttonColorSaveForeground = Color.WHITE;
		buttonColorDeleteForeground = null;
		buttonColorCancelForeground = new Color(149, 149, 149);
		buttonDefaultForeground = null;
		blueColorDefault = null;
		redColorDefault = null;
		textDefaultColor = new Color(0,0,0);
		backGroundDefaultColor = new Color(240, 240, 240);
		backgroundTitleDefaultColor = new Color(40, 41, 82);
		panelDefaultColor = null;
		buttonDefaultFont = new Font("Roboto", Font.BOLD, 15);
		buttonBiggerFont = new Font("Roboto", Font.BOLD, 17);
		labelDefaultFont = null;
		labelTitleFont = null;
		labelTitleFontBigger = null;
		textDefaultSize = null;
		textBiggerSize = null;
		logSize = null;
		buttonSaveBorder = new EmptyBorder(5, 30, 5, 30);
		buttonCancelBorder = new EmptyBorder(5, 30, 5, 30);
		buttonDeleteBorder = null;
		buttonDefaultBorder = null;
		textAreaBorder = new LineBorder(new Color(0,0,0));
		textFieldDefaultBorder = new LineBorder(new Color(0,0,0));
		errorMessageColor = Color.RED;
		
	}

	public Color getErrorMessageColor() {
		return errorMessageColor;
	}

	public Color getButtonColorSaveForeground() {
		return buttonColorSaveForeground;
	}

	public Color getButtonColorDeleteForeground() {
		return buttonColorDeleteForeground;
	}

	public Color getButtonColorCancelForeground() {
		return buttonColorCancelForeground;
	}

	public Color getButtonDefaultForeground() {
		return buttonDefaultForeground;
	}

	public Border getBorderTextArea() {
		return textAreaBorder;
	}

	public Border getBorderTextFieldDefault() {
		return textFieldDefaultBorder;
	}

	public Border getBorderSaveButton() {
		return buttonSaveBorder;
	}

	public Border getBorderCancelButton() {
		return buttonCancelBorder;
	}

	public Border getBorderDeleteButton() {
		return buttonDeleteBorder;
	}

	public Border getBorderDefaultButton() {
		return buttonDefaultBorder;
	}

	public Color getBackGroundDefaultColor() {
		return backGroundDefaultColor;
	}

	public Color getPanelDefaultColor() {
		return panelDefaultColor;
	}

	public Color getButtonColorSaved() {
		return buttonColorSavedBackground;
	}

	public Color getButtonColorDelete() {
		return buttonColorDeleteBackground;
	}

	public Color getButtonColorCancel() {
		return buttonColorCancelBackground;
	}


	public Color getButtonColorDefault() {
		return buttonColorDefaultBackground;
	}

	public Color getBlueColorDefault() {
		return blueColorDefault;
	}

	public Color getRedColorDefault() {
		return redColorDefault;
	}

	public Font getButtonDefaultFont() {
		return buttonDefaultFont;
	}

	public Font getButtonBiggerFont() {
		return buttonBiggerFont;
	}


	public Font getLabelDefaultFont() {
		return labelDefaultFont;
	}

	public Font getLabelTitleFont() {
		return labelTitleFont;
	}


	public Font getLabelTitleFontBigger() {
		return labelTitleFontBigger;
	}

	public Font getTextDefaultSize() {
		return textDefaultSize;
	}

	public Font getTextBiggerSize() {
		return textBiggerSize;
	}


	public Font getLogSize() {
		return logSize;
	}
	
	public Color getBackgroundTitleDefaultColor() {
		return backgroundTitleDefaultColor;
	}
	
	public Color getTextDefaultColor() {
		return textDefaultColor;
	}

}
