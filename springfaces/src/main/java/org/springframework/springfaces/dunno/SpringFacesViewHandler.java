package org.springframework.springfaces.dunno;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewDeclarationLanguage;

import org.springframework.springfaces.FacesView;

public class SpringFacesViewHandler extends ViewHandlerWrapper {

	private ViewHandler delegate;

	public SpringFacesViewHandler(ViewHandler delegate) {
		this.delegate = delegate;
	}

	@Override
	public ViewHandler getWrapped() {
		return delegate;
	}

	@Override
	public UIViewRoot createView(FacesContext context, String viewId) {
		return super.createView(context, convertViewId(viewId));
	}

	@Override
	public UIViewRoot restoreView(FacesContext context, String viewId) {
		return super.restoreView(context, convertViewId(viewId));
	}

	@Override
	public ViewDeclarationLanguage getViewDeclarationLanguage(FacesContext context, String viewId) {
		return super.getViewDeclarationLanguage(context, convertViewId(viewId));
	}

	@Override
	public String getActionURL(FacesContext context, String viewId) {
		if (FacesView.getCurrentInstance() != null) {
			//FIXME
			ExternalContext extContext = context.getExternalContext();
			String contextPath = extContext.getRequestContextPath();
			return contextPath + "/spring/simple";
		}
		return super.getActionURL(context, viewId);
	}

	private String convertViewId(String viewId) {
		if (FacesView.getCurrentInstance() != null) {
			return FacesView.getCurrentInstance().getUrl();
		}
		return viewId;
	}

}