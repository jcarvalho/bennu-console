package pt.ist.bennu.console.presentationTier.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pt.ist.bennu.core.domain.RoleType;
import pt.ist.bennu.core.domain.VirtualHost;
import pt.ist.bennu.core.domain.contents.Node;
import pt.ist.bennu.core.domain.groups.Role;
import pt.ist.bennu.core.presentationTier.actions.ContextBaseAction;
import pt.ist.bennu.vaadin.domain.contents.VaadinNode;
import pt.ist.fenixWebFramework.servlets.functionalities.CreateNodeAction;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;

@Mapping(path = "/bennuConsole")
public class BennuConsoleCreationAction extends ContextBaseAction {

    @CreateNodeAction(bundle = "BENNU_CONSOLE_RESOURCES", key = "label.module.bennu.console",
            groupKey = "label.module.bennu.console")
    public final ActionForward createBennuConsoleNode(final ActionMapping mapping, final ActionForm form,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final VirtualHost virtualHost = getDomainObject(request, "virtualHostToManageId");
        final Node node = getDomainObject(request, "parentOfNodesToManageId");

        VaadinNode.createVaadinNode(virtualHost, node, "resources.BennuConsoleResources", "label.module.bennu.console",
                "BennuConsole", Role.getRole(RoleType.MANAGER));

        return forwardToMuneConfiguration(request, virtualHost, node);
    }
}
