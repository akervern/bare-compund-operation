package org.nuxeo.sample;

import org.apache.commons.lang3.StringUtils;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.DocumentModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Operation(id = CompoundAttach.ID, category = Constants.CAT_DOCUMENT, label = "Attach Compound", description = "Describe here what your operation does.")
public class CompoundAttach {

    public static final String ID = "CompoundDocument.Attach";

    private static final Log log = LogFactory.getLog(CompoundAttach.class);

    @Param(name = "compoundDocs")
    protected List<String> compounds = new ArrayList<>();

    @OperationMethod
    public DocumentModel run(DocumentModel doc) {
        log.error(String.format("Attach [%s] to document %s (%s)", StringUtils.join(compounds, ", "), doc.getPathAsString(), doc.getId()));
        return doc;
    }
}
