/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.compare;

import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * ModelGenerator
 */
public interface ModelGenerator {
    
    // =========================================================================
    //                  Constants for execution errors
    // =========================================================================
    public static final int ERROR_PRODUCING_OUTPUT_MODEL                = 58001;
    public static final int RUNTIME_ERROR_PRODUCING_OUTPUT_MODEL        = 58002;
    public static final int USER_CANCELLED                              = 58003;
    public static final int COMPLETED_WITH_NO_PROBLEMS                  = 58004;
    public static final int COMPLETED_WITH_WARNINGS                     = 58005;
    public static final int COMPLETED_WITH_ERRORS                       = 58006;
    public static final int COMPLETED_WITH_WARNINGS_AND_ERRORS          = 58007;
    public static final int COMPLETED_WITH_NO_WARNINGS_AND_ERRORS       = 58008;
    
    /**
     * Obtain the description for this generator.  This method is used to help generate the status messages,
     * and therefore should never be null or zero-length.  Consequently, if the description is never set
     * or is {@link #setDescription(String) set} with a null or zero-length string, a 
     * {@link #getDefaultDescription() default description} is used.
     * @return the description; may not be null
     */
    public String getDescription();
    
    /**
     * Set the description for this generator.
     * @param desc the description; may be null
     */
    public void setDescription( final String desc );
    
    /**
     * Execute the generator.  This method invokes the producer to populate the output model,
     * performs a difference analysis between the output and original models,
     * and merges all changes so that the original is a mirror of the output.
     * @param monitor the progress monitor; may be null
     * @return the status containing the result of the generation and merge process
     */
    public IStatus execute( final IProgressMonitor progressMonitor );
    
    /**
     * Run the generator.  This method invokes the producer to populate the output model, but does not
     * performs a difference analysis between the output and original models, nor does it
     * merge changes so that the original is a mirror of the output.
     * This method should be called before
     * {@link #computeDifferenceReport(IProgressMonitor)} and before {@link #mergeOutputIntoOriginal(IProgressMonitor)}.
     * @param monitor the progress monitor; may be null
     * @return the status containing the result of the generation and merge process
     */
    public IStatus generateOutput( final IProgressMonitor progressMonitor );
    
    /**
     * After generating, compute the difference report.  This method should be called after
     * {@link #generateOutput(IProgressMonitor)} and before {@link #mergeOutputIntoOriginal(IProgressMonitor)}.
     * @param monitor the progress monitor; may be null
     * @return the status containing the result of the generation and merge process
     */
    public IStatus computeDifferenceReport( final IProgressMonitor progressMonitor );
    
    /**
     * Return the (potentially filtered) list of difference reports generated by 
     * {@link #generateOutputAndDifferenceReport(IProgressMonitor)} or by {@link #execute(IProgressMonitor)}. 
     * If the client is using {@link #generateOutputAndDifferenceReport(IProgressMonitor)}, individual 
     * nodes within the difference report nodes can be marked to be {@link DifferenceDescriptor#setSkip() skipped}
     * during the {@link #mergeOutputIntoOriginal(IProgressMonitor) merge} process.
     * <p>
     * The number of difference reports will vary by instance.
     * </p>
     * @return the list difference reports; never null, but may be empty if the reports haven't been computed
     * or if there are no exposed difference reports.
     * @see #getPrimaryDifferenceReport()
     * @see #getAllDifferenceReports()
     */
    public List getDifferenceReports();
    
    /**
     * Return the list of all difference reports generated by 
     * {@link #generateOutputAndDifferenceReport(IProgressMonitor)} or by {@link #execute(IProgressMonitor)}. 
     * If the client is using {@link #generateOutputAndDifferenceReport(IProgressMonitor)}, individual 
     * nodes within the difference report nodes can be marked to be {@link DifferenceDescriptor#setSkip() skipped}
     * during the {@link #mergeOutputIntoOriginal(IProgressMonitor) merge} process.
     * <p>
     * The number of difference reports will vary by instance.
     * </p>
     * @return the list difference reports; never null, but may be empty if the reports haven't been computed
     * or if there are no exposed difference reports.
     * @see #getPrimaryDifferenceReport()
     * @see #getAllDifferenceReports()
     */
    public List getAllDifferenceReports();

    /**
     * Execute the generator.  This method invokes the producer to populate the output model,
     * performs a difference analysis between the output and original models,
     * and merges all changes so that the original is a mirror of the output.
     * @param monitor the progress monitor; may be null
     * @return the status containing the result of the generation and merge process
     */
    public IStatus mergeOutputIntoOriginal( final IProgressMonitor progressMonitor );
    
    /**
     * Close any resources that were opened by this generator.  This method should be called when finished
     * with this object.
     */
    public void close();
    
    /**
     * method provides visibility for a generator to operate in both a model "Update" mode or a new model case.
     * @return
     * @since 5.0.2
     */
    public boolean isNewModelCase();
    
    /**
     * Set's the generator's isNewModelCase parameter. (see isNewModelCase() method)
     * @param theIsNewModelCase
     * @since 5.0.2
     */
    public void setNewModelCase(boolean theIsNewModelCase);
    
    /**
     * Sets the generator's saveAllBeforeFinish parameter. (see isSaveAllBeforeFinish() method)
     * @param theDoSave
     * @since 5.0.2
     */
    public void setSaveAllBeforeFinish(boolean theDoSave);
    
    /**
     * method provides visibility to a generator's save intentions (i.e. save before finish).  We needed a method to prevent any
     * saving of resources prior to model creation (including updating imports) being completely finished.
     * @return
     * @since 5.0.2
     */
    public boolean isSaveAllBeforeFinish();
    
    /**
     *  allows builders & actions to specifically do a save on all resources associated with the action/builder's generator(s)
     * 
     * @since 5.0.2
     */
    public void saveModel();
    
}