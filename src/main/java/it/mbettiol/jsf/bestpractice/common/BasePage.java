package it.mbettiol.jsf.bestpractice.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public abstract class BasePage {

	private ComponentMap cm;
	
	public BasePage(){
		/* default */
		cm = new ComponentMap(this);
		
	}
	
	protected UIComponent getPageComponent(String key){
		return cm.get(key);
	}
	
	public Map<String, UIComponent> getComponents(){
		return cm;
	}
	
	private static class ComponentMap implements Map<String, UIComponent>{

		private static final String PAGE_COMPONENT_BINDINGS = BasePage.class.getName()+".PAGE_COMPONENT_BINDINGS";
		
		private BasePage basePage;
		private String prefix;
		
		private Map<String, UIComponent> getOrCreate(){
			HttpServletRequest request = basePage.getRequest();
			@SuppressWarnings("unchecked")
			Map<String, UIComponent> map  = (Map<String, UIComponent>) request.getAttribute(PAGE_COMPONENT_BINDINGS);
			if(map==null){
				map = new HashMap<String, UIComponent>();
				request.setAttribute(PAGE_COMPONENT_BINDINGS, map);
			}
			return map;
		}
		
		protected ComponentMap(BasePage basePage){
			this.basePage = basePage;
			this.prefix = basePage.getClass().getName()+"-";
		}
		public int size() {
			throw new UnsupportedOperationException();
		}

		public boolean isEmpty() {
			throw new UnsupportedOperationException();		}

		public boolean containsKey(Object key) {
			throw new UnsupportedOperationException();		}

		public boolean containsValue(Object value) {
			throw new UnsupportedOperationException();
		}

		public UIComponent get(Object key) {
			return getOrCreate().get(prefix+"-"+key);
		}

		public UIComponent put(String key, UIComponent value) {
			return getOrCreate().put(prefix+"-"+key, value);
		}

		public UIComponent remove(Object key) {
			throw new UnsupportedOperationException();
		}

		public void putAll(Map<? extends String, ? extends UIComponent> m) {
			throw new UnsupportedOperationException();
		}

		public void clear() {
			throw new UnsupportedOperationException();
		}

		public Set<String> keySet() {
			throw new UnsupportedOperationException();
		}

		public Collection<UIComponent> values() {
			throw new UnsupportedOperationException();
		}

		public Set<java.util.Map.Entry<String, UIComponent>> entrySet() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	protected HttpServletRequest getRequest(){
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
}
