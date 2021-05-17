package config;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author Group 1 dmai0920
 * This class serves as a premade configuration of all the colors, fonts and styles of the UI elements
 */
public class Config
{
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
	private Color separatorColor;
	private Color selectedSidebarButtonColor;
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
	private Border emptyBorderZeros;
	private Border sidebarButtonBorder;
	
	public Config()
	{
		//TODO finish implementing these
		buttonColorSavedBackground = new Color(86, 197, 104);
		buttonColorDeleteBackground = new Color(235, 87, 87);
		buttonColorCancelBackground = new Color(200, 200, 200);
		buttonColorDefaultBackground = new Color(40,41,81);
		buttonColorSaveForeground = Color.WHITE;
		buttonColorDeleteForeground = Color.WHITE;
		buttonColorCancelForeground = new Color(140, 140, 140);
		buttonDefaultForeground = Color.WHITE;
		blueColorDefault = new Color(40, 41, 82);
		redColorDefault = null;
		textDefaultColor = new Color(0,0,0);
		backGroundDefaultColor = new Color(240, 240, 240);
		panelDefaultColor = null;
		buttonDefaultFont = new Font("Roboto", Font.BOLD, 15);
		buttonBiggerFont = new Font("Roboto", Font.BOLD, 17);
		labelDefaultFont = new Font("Roboto", Font.PLAIN, 13);
		labelTitleFont = new Font("Roboto", Font.BOLD, 15);
		labelTitleFontBigger = new Font("Roboto", Font.BOLD, 17);
		textDefaultSize = new Font("Roboto", Font.PLAIN, 12);
		textBiggerSize = null;
		logSize = null;
		emptyBorderZeros = new EmptyBorder(0, 0, 0, 0);
		buttonSaveBorder = new EmptyBorder(5, 30, 5, 30);
		buttonCancelBorder = new EmptyBorder(5, 30, 5, 30);
		buttonDeleteBorder = new LineBorder(new Color(240, 0, 0), 2);
		buttonDefaultBorder = null;
		sidebarButtonBorder = new EmptyBorder(15, 10, 15, 10);
		textAreaBorder = new LineBorder(new Color(0,0,0));
		textFieldDefaultBorder = new LineBorder(new Color(0,0,0));
		errorMessageColor = Color.RED;
		separatorColor = new Color(196, 196, 196);
		selectedSidebarButtonColor = new Color(234, 234, 238);
		
	}

	public Color getButtonColorSavedBackground() {
		return buttonColorSavedBackground;
	}

	public void setButtonColorSavedBackground(Color buttonColorSavedBackground) {
		this.buttonColorSavedBackground = buttonColorSavedBackground;
	}

	public Color getButtonColorDeleteBackground() {
		return buttonColorDeleteBackground;
	}

	public void setButtonColorDeleteBackground(Color buttonColorDeleteBackground) {
		this.buttonColorDeleteBackground = buttonColorDeleteBackground;
	}

	public Color getButtonColorCancelBackground() {
		return buttonColorCancelBackground;
	}

	public void setButtonColorCancelBackground(Color buttonColorCancelBackground) {
		this.buttonColorCancelBackground = buttonColorCancelBackground;
	}

	public Color getButtonColorDefaultBackground() {
		return buttonColorDefaultBackground;
	}

	public void setButtonColorDefaultBackground(Color buttonColorDefaultBackground) {
		this.buttonColorDefaultBackground = buttonColorDefaultBackground;
	}

	public Color getButtonColorSaveForeground() {
		return buttonColorSaveForeground;
	}

	public void setButtonColorSaveForeground(Color buttonColorSaveForeground) {
		this.buttonColorSaveForeground = buttonColorSaveForeground;
	}

	public Color getButtonColorDeleteForeground() {
		return buttonColorDeleteForeground;
	}

	public void setButtonColorDeleteForeground(Color buttonColorDeleteForeground) {
		this.buttonColorDeleteForeground = buttonColorDeleteForeground;
	}

	public Color getButtonColorCancelForeground() {
		return buttonColorCancelForeground;
	}

	public void setButtonColorCancelForeground(Color buttonColorCancelForeground) {
		this.buttonColorCancelForeground = buttonColorCancelForeground;
	}

	public Color getButtonDefaultForeground() {
		return buttonDefaultForeground;
	}

	public void setButtonDefaultForeground(Color buttonDefaultForeground) {
		this.buttonDefaultForeground = buttonDefaultForeground;
	}

	public Color getBlueColorDefault() {
		return blueColorDefault;
	}

	public void setBlueColorDefault(Color blueColorDefault) {
		this.blueColorDefault = blueColorDefault;
	}

	public Color getRedColorDefault() {
		return redColorDefault;
	}

	public void setRedColorDefault(Color redColorDefault) {
		this.redColorDefault = redColorDefault;
	}

	public Color getBackGroundDefaultColor() {
		return backGroundDefaultColor;
	}

	public void setBackGroundDefaultColor(Color backGroundDefaultColor) {
		this.backGroundDefaultColor = backGroundDefaultColor;
	}

	public Color getPanelDefaultColor() {
		return panelDefaultColor;
	}

	public void setPanelDefaultColor(Color panelDefaultColor) {
		this.panelDefaultColor = panelDefaultColor;
	}

	public Color getBackgroundTitleDefaultColor() {
		return backgroundTitleDefaultColor;
	}

	public void setBackgroundTitleDefaultColor(Color backgroundTitleDefaultColor) {
		this.backgroundTitleDefaultColor = backgroundTitleDefaultColor;
	}

	public Color getTextDefaultColor() {
		return textDefaultColor;
	}

	public void setTextDefaultColor(Color textDefaultColor) {
		this.textDefaultColor = textDefaultColor;
	}

	public Color getErrorMessageColor() {
		return errorMessageColor;
	}

	public void setErrorMessageColor(Color errorMessageColor) {
		this.errorMessageColor = errorMessageColor;
	}

	public Color getSeparatorColor() {
		return separatorColor;
	}

	public void setSeparatorColor(Color separatorColor) {
		this.separatorColor = separatorColor;
	}

	public Color getSelectedSidebarButtonColor() {
		return selectedSidebarButtonColor;
	}

	public void setSelectedSidebarButtonColor(Color selectedSidebarButtonColor) {
		this.selectedSidebarButtonColor = selectedSidebarButtonColor;
	}

	public Font getButtonDefaultFont() {
		return buttonDefaultFont;
	}

	public void setButtonDefaultFont(Font buttonDefaultFont) {
		this.buttonDefaultFont = buttonDefaultFont;
	}

	public Font getButtonBiggerFont() {
		return buttonBiggerFont;
	}

	public void setButtonBiggerFont(Font buttonBiggerFont) {
		this.buttonBiggerFont = buttonBiggerFont;
	}

	public Font getLabelDefaultFont() {
		return labelDefaultFont;
	}

	public void setLabelDefaultFont(Font labelDefaultFont) {
		this.labelDefaultFont = labelDefaultFont;
	}

	public Font getLabelTitleFont() {
		return labelTitleFont;
	}

	public void setLabelTitleFont(Font labelTitleFont) {
		this.labelTitleFont = labelTitleFont;
	}

	public Font getLabelTitleFontBigger() {
		return labelTitleFontBigger;
	}

	public void setLabelTitleFontBigger(Font labelTitleFontBigger) {
		this.labelTitleFontBigger = labelTitleFontBigger;
	}

	public Font getTextDefaultSize() {
		return textDefaultSize;
	}

	public void setTextDefaultSize(Font textDefaultSize) {
		this.textDefaultSize = textDefaultSize;
	}

	public Font getTextBiggerSize() {
		return textBiggerSize;
	}

	public void setTextBiggerSize(Font textBiggerSize) {
		this.textBiggerSize = textBiggerSize;
	}

	public Font getLogSize() {
		return logSize;
	}

	public void setLogSize(Font logSize) {
		this.logSize = logSize;
	}

	public Border getButtonSaveBorder() {
		return buttonSaveBorder;
	}

	public void setButtonSaveBorder(Border buttonSaveBorder) {
		this.buttonSaveBorder = buttonSaveBorder;
	}

	public Border getButtonCancelBorder() {
		return buttonCancelBorder;
	}

	public void setButtonCancelBorder(Border buttonCancelBorder) {
		this.buttonCancelBorder = buttonCancelBorder;
	}

	public Border getButtonDeleteBorder() {
		return buttonDeleteBorder;
	}

	public void setButtonDeleteBorder(Border buttonDeleteBorder) {
		this.buttonDeleteBorder = buttonDeleteBorder;
	}

	public Border getButtonDefaultBorder() {
		return buttonDefaultBorder;
	}

	public void setButtonDefaultBorder(Border buttonDefaultBorder) {
		this.buttonDefaultBorder = buttonDefaultBorder;
	}

	public Border getTextAreaBorder() {
		return textAreaBorder;
	}

	public void setTextAreaBorder(Border textAreaBorder) {
		this.textAreaBorder = textAreaBorder;
	}

	public Border getTextFieldDefaultBorder() {
		return textFieldDefaultBorder;
	}

	public void setTextFieldDefaultBorder(Border textFieldDefaultBorder) {
		this.textFieldDefaultBorder = textFieldDefaultBorder;
	}

	public Border getEmptyBorderZeros() {
		return emptyBorderZeros;
	}

	public void setEmptyBorderZeros(Border emptyBorderZeros) {
		this.emptyBorderZeros = emptyBorderZeros;
	}

	public Border getSidebarButtonBorder() {
		return sidebarButtonBorder;
	}

	public void setSidebarButtonBorder(Border sidebarButtonBorder) {
		this.sidebarButtonBorder = sidebarButtonBorder;
	}

	
}
