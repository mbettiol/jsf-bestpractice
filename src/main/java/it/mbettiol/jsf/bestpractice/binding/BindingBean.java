package it.mbettiol.jsf.bestpractice.binding;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;

import it.mbettiol.jsf.bestpractice.common.BasePage;

public class BindingBean extends BasePage {
	
	private int counter;
	
	// MAP BINDING
	public void addMessage1(){
		UIComponent pageComponent = getPageComponent("PANEL1");
		addMessage(pageComponent);
	}
	
	//BEGIN EASY REFACTOR
	
	public void addMessage2(){
		UIComponent panel = getPanel();
		addMessage(panel);
	}
	
	public void setPanel(UIComponent component){
		getComponents().put("PANEL2", component);
	}
	
	public UIComponent getPanel(){
		return getComponents().get("PANEL2");
	}
	
	//END EASY REFACTOR
	
	
	private void addMessage(UIComponent panel){
		counter++;
		HtmlOutputText htmlOutputText = new HtmlOutputText();
		htmlOutputText.setValue("messagggio : "+counter);
		panel.getChildren().add(htmlOutputText);
	}
	
	
}
