package dot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import gui.UmlWrapper;
import phases.GenerateUML;
import phases.IPhase;
import phases.Load;
import phases.PatternDetection;
import records.ClassRecord;
import records.IClassRecord;

public class CompositeTest {
	@Test
	public void testNotComposite() throws IOException, ClassNotFoundException {
		assertComposite(new String[] { "headfirst.composite.menu.MenuItem", "headfirst.composite.menu.MenuComponent"}, new String[0], new String[0],
				new String[0], new String[] { "headfirst.composite.menu.MenuItem", "headfirst.composite.menu.MenuComponent" }, "");
	}

	@Test
	public void testComposite() throws IOException, ClassNotFoundException {
		assertComposite(new String[] { "headfirst.composite.menu.Menu", "headfirst.composite.menu.MenuComponent", "headfirst.composite.menu.MenuItem"}, new String[]{"headfirst.composite.menu.MenuComponent"}, new String[]{"headfirst.composite.menu.Menu"},
				new String[]{"headfirst.composite.menu.MenuItem"}, new String[0], "digraph G {fontname = \"Comic Sans MS\"  fontsize = 8  node [ fontname = \"Comic Sans MS\" fontsize = 8 shape = \"record\"] edge [ fontname = \"Comic Sans MS\" fontsize = 8 ]\n" +
						"Menu [color = \"yellow\" label = \"{Menu\\n\\<\\<Composite\\>\\>|+name : java.lang.String\\l\n" +
						"+description : java.lang.String\\l\n" +
						"+menuComponents : java.util.ArrayList\\l\n" +
						"|+ removeheadfirst.composite.menu.MenuComponent  : void\\l\n" +
						"+ print : void\\l\n" +
						"+ getName : java.lang.String\\l\n" +
						"+ getDescription : java.lang.String\\l\n" +
						"+ addheadfirst.composite.menu.MenuComponent  : void\\l\n" +
						"+ getChildint  : headfirst.composite.menu.MenuComponent\\l\n" +
						"}\"]edge [ style = \"normal\", arrowhead = \"normal\"]\n" +
						"Menu -> MenuComponent\n" +
						"edge [ style = \"dotted\" arrowhead = \"open\"]\n" +
						"Menu -> MenuComponent\n" +
						"MenuComponent [color = \"yellow\" label = \"{MenuComponent\\n\\<\\<Component\\>\\>||+ removeheadfirst.composite.menu.MenuComponent  : void\\l\n" +
						"+ getPrice : double\\l\n" +
						"+ print : void\\l\n" +
						"+ getName : java.lang.String\\l\n" +
						"+ getDescription : java.lang.String\\l\n" +
						"+ isVegetarian : boolean\\l\n" +
						"+ addheadfirst.composite.menu.MenuComponent  : void\\l\n" +
						"+ getChildint  : headfirst.composite.menu.MenuComponent\\l\n" +
						"}\"]edge [ style = \"dotted\" arrowhead = \"open\"]\n" +
						"MenuComponent -> MenuComponent\n" +
						"MenuItem [color = \"yellow\" label = \"{MenuItem\\n\\<\\<Leaf\\>\\>|+name : java.lang.String\\l\n" +
						"+description : java.lang.String\\l\n" +
						"+vegetarian : boolean\\l\n" +
						"+price : double\\l\n" +
						"|+ getPrice : double\\l\n" +
						"+ print : void\\l\n" +
						"+ getName : java.lang.String\\l\n" +
						"+ getDescription : java.lang.String\\l\n" +
						"+ isVegetarian : boolean\\l\n" +
						"}\"]edge [ style = \"normal\", arrowhead = \"normal\"]\n" +
						"MenuItem -> MenuComponent\n" +
						"}");
	}
	
	@Test
	public void testSwingIsComposite() throws IOException, ClassNotFoundException {
		assertComposite(new String[] { "java.awt.Container", "java.awt.Component", "java.awt.Button"}, new String[]{"java.awt.Component"}, new String[]{"java.awt.Container"},
				new String[]{"java.awt.Button"}, new String[0],
				"digraph G {fontname = \"Comic Sans MS\"  fontsize = 8  node [ fontname = \"Comic Sans MS\" fontsize = 8 shape = \"record\"] edge [ fontname = \"Comic Sans MS\" fontsize = 8 ]\n" +
						"Container [color = \"yellow\" label = \"{Container\\n\\<\\<Composite\\>\\>|+mixingLog : sun.util.logging.PlatformLogger\\l\n" +
						"+descendantsCount : int\\l\n" +
						"+focusTraversalPolicy : java.awt.FocusTraversalPolicy\\l\n" +
						"+containerSerializedDataVersion : int\\l\n" +
						"+SEARCH_HEAVYWEIGHTS : boolean\\l\n" +
						"+eventLog : sun.util.logging.PlatformLogger\\l\n" +
						"+preserveBackgroundColor : java.awt.Color\\l\n" +
						"+listeningBoundsChildren : int\\l\n" +
						"+containerListener : java.awt.event.ContainerListener\\l\n" +
						"+numOfHWComponents : int\\l\n" +
						"+printingThreads : java.util.Set\\l\n" +
						"+INCLUDE_SELF : boolean\\l\n" +
						"+modalComp : java.awt.Component\\l\n" +
						"+component : java.util.List\\l\n" +
						"+printing : boolean\\l\n" +
						"+modalAppContext : sun.awt.AppContext\\l\n" +
						"+isJavaAwtSmartInvalidate : boolean\\l\n" +
						"+serialVersionUID : long\\l\n" +
						"+descendUnconditionallyWhenValidating : boolean\\l\n" +
						"+EMPTY_ARRAY : java.awt.Component[]\\l\n" +
						"+listeningChildren : int\\l\n" +
						"+numOfLWComponents : int\\l\n" +
						"+serialPersistentFields : java.io.ObjectStreamField[]\\l\n" +
						"+log : sun.util.logging.PlatformLogger\\l\n" +
						"+layoutMgr : java.awt.LayoutManager\\l\n" +
						"+focusCycleRoot : boolean\\l\n" +
						"+dispatcher : java.awt.LightweightDispatcher\\l\n" +
						"+focusTraversalPolicyProvider : boolean\\l\n" +
						"|+ recursiveApplyCurrentShapeint int  : void\\l\n" +
						"+ getChildAtjava.awt.Component int int boolean  : java.awt.Component\\l\n" +
						"+ isRecursivelyVisibleUpToHeavyweightContainer : boolean\\l\n" +
						"+ clearMostRecentFocusOwnerOnHide : void\\l\n" +
						"+ listjava.io.PrintStream int  : void\\l\n" +
						"+ countHierarchyMembers : int\\l\n" +
						"+ removeNotify : void\\l\n" +
						"+ printjava.awt.Graphics  : void\\l\n" +
						"+ writeObjectjava.io.ObjectOutputStream  : void\\l\n" +
						"+ deliverEventjava.awt.Event  : void\\l\n" +
						"+ getMaximumSize : java.awt.Dimension\\l\n" +
						"+ getTraversalRoot : java.awt.Container\\l\n" +
						"+ isAncestorOfjava.awt.Component  : boolean\\l\n" +
						"+ listjava.io.PrintWriter int  : void\\l\n" +
						"+ recursiveSubtractAndApplyShapesun.java2d.pipe.Region int  : void\\l\n" +
						"+ getMousePositionboolean  : java.awt.Point\\l\n" +
						"+ processContainerEventjava.awt.event.ContainerEvent  : void\\l\n" +
						"+ mixOnShowing : void\\l\n" +
						"+ findComponentAtjava.awt.Point  : java.awt.Component\\l\n" +
						"+ getMouseEventTargetImplint int boolean java.awt.Container$EventTargetFilter boolean boolean  : java.awt.Component\\l\n" +
						"+ initIDs : void\\l\n" +
						"+ applyComponentOrientationjava.awt.ComponentOrientation  : void\\l\n" +
						"+ addPropertyChangeListenerjava.lang.String java.beans.PropertyChangeListener  : void\\l\n" +
						"+ areFocusTraversalKeysSetint  : boolean\\l\n" +
						"+ getInsets : java.awt.Insets\\l\n" +
						"+ invalidateParent : void\\l\n" +
						"+ setFocusTraversalPolicyjava.awt.FocusTraversalPolicy  : void\\l\n" +
						"+ getAccessibleChildrenCount : int\\l\n" +
						"+ validate : void\\l\n" +
						"+ adjustDescendantsint  : void\\l\n" +
						"+ isFocusTraversalPolicyProvider : boolean\\l\n" +
						"+ addjava.awt.Component int  : java.awt.Component\\l\n" +
						"+ getBottommostComponentIndex : int\\l\n" +
						"+ addjava.awt.Component java.lang.Object int  : void\\l\n" +
						"+ removejava.awt.Component  : void\\l\n" +
						"+ getComponentAtjava.awt.Point  : java.awt.Component\\l\n" +
						"+ getContainerListeners : java.awt.event.ContainerListener[]\\l\n" +
						"+ getComponentCount : int\\l\n" +
						"+ insets : java.awt.Insets\\l\n" +
						"+ paramString : java.lang.String\\l\n" +
						"+ getOpaqueShape : sun.java2d.pipe.Region\\l\n" +
						"+ recursiveSubtractAndApplyShapesun.java2d.pipe.Region int int  : void\\l\n" +
						"+ getFocusTraversalPolicy : java.awt.FocusTraversalPolicy\\l\n" +
						"+ recursiveHideHeavyweightChildren : void\\l\n" +
						"+ getListenersCountint boolean  : int\\l\n" +
						"+ getFocusTraversalKeysint  : java.util.Set\\l\n" +
						"+ hasHeavyweightDescendants : boolean\\l\n" +
						"+ proxyEnableEventslong  : void\\l\n" +
						"+ canContainFocusOwnerjava.awt.Component  : boolean\\l\n" +
						"+ reparentChildjava.awt.Component  : void\\l\n" +
						"+ preferredSize : java.awt.Dimension\\l\n" +
						"+ printComponentsjava.awt.Graphics  : void\\l\n" +
						"+ postsOldMouseEvents : boolean\\l\n" +
						"+ isValidateRoot : boolean\\l\n" +
						"+ addjava.awt.Component java.lang.Object  : void\\l\n" +
						"+ postProcessKeyEventjava.awt.event.KeyEvent  : void\\l\n" +
						"+ mixOnHidingboolean  : void\\l\n" +
						"+ getComponentZOrderjava.awt.Component  : int\\l\n" +
						"+ clearCurrentFocusCycleRootOnHide : void\\l\n" +
						"+ doLayout : void\\l\n" +
						"+ adjustDecendantsOnParentint  : void\\l\n" +
						"+ setFocusTraversalPolicyProviderboolean  : void\\l\n" +
						"+ lightweightPaintjava.awt.Graphics  : void\\l\n" +
						"+ countComponents : int\\l\n" +
						"+ locateint int  : java.awt.Component\\l\n" +
						"+ getComponents : java.awt.Component[]\\l\n" +
						"+ addjava.lang.String java.awt.Component  : java.awt.Component\\l\n" +
						"+ readObjectjava.io.ObjectInputStream  : void\\l\n" +
						"+ findTraversalRoot : java.awt.Container\\l\n" +
						"+ addjava.awt.Component  : java.awt.Component\\l\n" +
						"+ getHeavyweightContainer : java.awt.Container\\l\n" +
						"+ getComponentAtint int  : java.awt.Component\\l\n" +
						"+ updatejava.awt.Graphics  : void\\l\n" +
						"+ setLayoutjava.awt.LayoutManager  : void\\l\n" +
						"+ getAccessibleChildint  : javax.accessibility.Accessible\\l\n" +
						"+ updateGraphicsDatajava.awt.GraphicsConfiguration  : boolean\\l\n" +
						"+ addContainerListenerjava.awt.event.ContainerListener  : void\\l\n" +
						"+ getAlignmentY : float\\l\n" +
						"+ isFocusTraversalPolicySet : boolean\\l\n" +
						"+ getAlignmentX : float\\l\n" +
						"+ decreaseComponentCountjava.awt.Component  : void\\l\n" +
						"+ getComponentint  : java.awt.Component\\l\n" +
						"+ lightweightPrintjava.awt.Graphics  : void\\l\n" +
						"+ initializeFocusTraversalKeys : void\\l\n" +
						"+ stopLWModal : void\\l\n" +
						"+ getMinimumSize : java.awt.Dimension\\l\n" +
						"+ checkAddingjava.awt.Component int  : void\\l\n" +
						"+ getPreferredSize : java.awt.Dimension\\l\n" +
						"+ dispatchEventImpljava.awt.AWTEvent  : void\\l\n" +
						"+ addNotify : void\\l\n" +
						"+ layout : void\\l\n" +
						"+ setFocusTraversalKeysint java.util.Set  : void\\l\n" +
						"+ createChildHierarchyEventsint long boolean  : void\\l\n" +
						"+ containsFocus : boolean\\l\n" +
						"+ isFocusCycleRoot : boolean\\l\n" +
						"+ getTopmostComponentIndex : int\\l\n" +
						"+ removeContainerListenerjava.awt.event.ContainerListener  : void\\l\n" +
						"+ getAccessibleAtjava.awt.Point  : javax.accessibility.Accessible\\l\n" +
						"+ removeAll : void\\l\n" +
						"+ validateUnconditionally : void\\l\n" +
						"+ paintHeavyweightComponentsjava.awt.Graphics  : void\\l\n" +
						"+ addPropertyChangeListenerjava.beans.PropertyChangeListener  : void\\l\n" +
						"+ numListeninglong  : int\\l\n" +
						"+ getMouseEventTargetint int boolean java.awt.Container$EventTargetFilter boolean  : java.awt.Component\\l\n" +
						"+ recursiveShowHeavyweightChildren : void\\l\n" +
						"+ mixOnZOrderChangingint int  : void\\l\n" +
						"+ adjustListeningChildrenlong int  : void\\l\n" +
						"+ transferFocusDownCycle : void\\l\n" +
						"+ checkAddToSelfjava.awt.Component  : void\\l\n" +
						"+ validateTree : void\\l\n" +
						"+ paintjava.awt.Graphics  : void\\l\n" +
						"+ dispatchEventToSelfjava.awt.AWTEvent  : void\\l\n" +
						"+ reparentTraversejava.awt.peer.ContainerPeer java.awt.Container  : void\\l\n" +
						"+ removeint  : void\\l\n" +
						"+ recursiveApplyCurrentShapeint  : void\\l\n" +
						"+ checkNotAWindowjava.awt.Component  : void\\l\n" +
						"+ findComponentAtint int  : java.awt.Component\\l\n" +
						"+ eventEnabledjava.awt.AWTEvent  : boolean\\l\n" +
						"+ getMouseEventTargetint int boolean  : java.awt.Component\\l\n" +
						"+ getComponentsSync : java.awt.Component[]\\l\n" +
						"+ preProcessKeyEventjava.awt.event.KeyEvent  : void\\l\n" +
						"+ addDelicatelyjava.awt.Component java.awt.Container int  : void\\l\n" +
						"+ checkGDjava.lang.String  : void\\l\n" +
						"+ invalidate : void\\l\n" +
						"+ createHierarchyEventsint java.awt.Component java.awt.Container long boolean  : int\\l\n" +
						"+ setFontjava.awt.Font  : void\\l\n" +
						"+ setFocusCycleRootboolean  : void\\l\n" +
						"+ isParentOfjava.awt.Component  : boolean\\l\n" +
						"+ getComponents_NoClientCode : java.awt.Component[]\\l\n" +
						"+ recursiveApplyCurrentShape : void\\l\n" +
						"+ getListenersjava.lang.Class  : java.util.EventListener[]\\l\n" +
						"+ invalidateTree : void\\l\n" +
						"+ removeDelicatelyjava.awt.Component java.awt.Container int  : boolean\\l\n" +
						"+ setComponentZOrderjava.awt.Component int  : void\\l\n" +
						"+ getLayout : java.awt.LayoutManager\\l\n" +
						"+ recursiveSubtractAndApplyShapesun.java2d.pipe.Region  : void\\l\n" +
						"+ minimumSize : java.awt.Dimension\\l\n" +
						"+ paintComponentsjava.awt.Graphics  : void\\l\n" +
						"+ findComponentAtImplint int boolean  : java.awt.Component\\l\n" +
						"+ startLWModal : void\\l\n" +
						"+ hasLightweightDescendants : boolean\\l\n" +
						"+ increaseComponentCountjava.awt.Component  : void\\l\n" +
						"+ isRemoveNotifyNeededjava.awt.Component java.awt.Container java.awt.Container  : boolean\\l\n" +
						"+ addImpljava.awt.Component java.lang.Object int  : void\\l\n" +
						"+ processEventjava.awt.AWTEvent  : void\\l\n" +
						"+ mixOnValidating : void\\l\n" +
						"+ isFocusCycleRootjava.awt.Container  : boolean\\l\n" +
						"+ isSameOrAncestorOfjava.awt.Component boolean  : boolean\\l\n" +
						"+ findComponentAtint int boolean  : java.awt.Component\\l\n" +
						"+ mixOnReshaping : void\\l\n" +
						"+ printHeavyweightComponentsjava.awt.Graphics  : void\\l\n" +
						"+ recursiveRelocateHeavyweightChildrenjava.awt.Point  : void\\l\n" +
						"+ getDropTargetEventTargetint int boolean  : java.awt.Component\\l\n" +
						"}\"]edge [ style = \"normal\", arrowhead = \"normal\"]\n" +
						"Container -> Component\n" +
						"edge [ style = \"dotted\" arrowhead = \"open\"]\n" +
						"Container -> Container\n" +
						"Container -> Component\n" +
						"Component [color = \"yellow\" label = \"{Component\\n\\<\\<Component\\>\\>|+mouseListener : java.awt.event.MouseListener\\l\n" +
						"+appContext : sun.awt.AppContext\\l\n" +
						"+font : java.awt.Font\\l\n" +
						"+foreground : java.awt.Color\\l\n" +
						"+autoFocusTransferOnDisposal : boolean\\l\n" +
						"+mouseWheelListenerK : java.lang.String\\l\n" +
						"+dropTarget : java.awt.dnd.DropTarget\\l\n" +
						"+itemListenerK : java.lang.String\\l\n" +
						"+width : int\\l\n" +
						"+graphicsConfig : java.awt.GraphicsConfiguration\\l\n" +
						"+changeSupport : java.beans.PropertyChangeSupport\\l\n" +
						"+FOCUS_TRAVERSABLE_DEFAULT : int\\l\n" +
						"+actionListenerK : java.lang.String\\l\n" +
						"+FOCUS_TRAVERSABLE_SET : int\\l\n" +
						"+accessibleContext : javax.accessibility.AccessibleContext\\l\n" +
						"+nameExplicitlySet : boolean\\l\n" +
						"+textListenerK : java.lang.String\\l\n" +
						"+BOTTOM_ALIGNMENT : float\\l\n" +
						"+focusLog : sun.util.logging.PlatformLogger\\l\n" +
						"+FOCUS_TRAVERSABLE_UNKNOWN : int\\l\n" +
						"+mixingCutoutRegion : sun.java2d.pipe.Region\\l\n" +
						"+prefSizeSet : boolean\\l\n" +
						"+height : int\\l\n" +
						"+eventMask : long\\l\n" +
						"+hierarchyListenerK : java.lang.String\\l\n" +
						"+prefSize : java.awt.Dimension\\l\n" +
						"+popups : java.util.Vector\\l\n" +
						"+visible : boolean\\l\n" +
						"+bufferStrategy : java.awt.image.BufferStrategy\\l\n" +
						"+focusListener : java.awt.event.FocusListener\\l\n" +
						"+eventCache : sun.awt.EventQueueItem[]\\l\n" +
						"+cursor : java.awt.Cursor\\l\n" +
						"+CENTER_ALIGNMENT : float\\l\n" +
						"+compoundShape : sun.java2d.pipe.Region\\l\n" +
						"+$assertionsDisabled : boolean\\l\n" +
						"+componentListenerK : java.lang.String\\l\n" +
						"+hierarchyBoundsListener : java.awt.event.HierarchyBoundsListener\\l\n" +
						"+LEFT_ALIGNMENT : float\\l\n" +
						"+containerListenerK : java.lang.String\\l\n" +
						"+eventLog : sun.util.logging.PlatformLogger\\l\n" +
						"+componentListener : java.awt.event.ComponentListener\\l\n" +
						"+peerFont : java.awt.Font\\l\n" +
						"+locale : java.util.Locale\\l\n" +
						"+newEventsOnly : boolean\\l\n" +
						"+LOCK : java.lang.Object\\l\n" +
						"+focusTraversalKeys : java.util.Set[]\\l\n" +
						"+isInc : boolean\\l\n" +
						"+mouseListenerK : java.lang.String\\l\n" +
						"+minSize : java.awt.Dimension\\l\n" +
						"+mouseMotionListenerK : java.lang.String\\l\n" +
						"+isFocusTraversableOverridden : int\\l\n" +
						"+parent : java.awt.Container\\l\n" +
						"+x : int\\l\n" +
						"+y : int\\l\n" +
						"+incRate : int\\l\n" +
						"+hierarchyListener : java.awt.event.HierarchyListener\\l\n" +
						"+serialVersionUID : long\\l\n" +
						"+backgroundEraseDisabled : boolean\\l\n" +
						"+TOP_ALIGNMENT : float\\l\n" +
						"+windowClosingException : java.lang.RuntimeException\\l\n" +
						"+coalescingEnabled : boolean\\l\n" +
						"+mixingLog : sun.util.logging.PlatformLogger\\l\n" +
						"+enabled : boolean\\l\n" +
						"+name : java.lang.String\\l\n" +
						"+windowListenerK : java.lang.String\\l\n" +
						"+maxSize : java.awt.Dimension\\l\n" +
						"+boundsOp : int\\l\n" +
						"+peer : java.awt.peer.ComponentPeer\\l\n" +
						"+maxSizeSet : boolean\\l\n" +
						"+inputMethodListenerK : java.lang.String\\l\n" +
						"+coalesceMap : java.util.Map\\l\n" +
						"+focusTraversalKeysEnabled : boolean\\l\n" +
						"+requestFocusController : sun.awt.RequestFocusController\\l\n" +
						"+focusable : boolean\\l\n" +
						"+background : java.awt.Color\\l\n" +
						"+windowStateListenerK : java.lang.String\\l\n" +
						"+adjustmentListenerK : java.lang.String\\l\n" +
						"+ownedWindowK : java.lang.String\\l\n" +
						"+mouseMotionListener : java.awt.event.MouseMotionListener\\l\n" +
						"+windowFocusListenerK : java.lang.String\\l\n" +
						"+focusTraversalKeyPropertyNames : java.lang.String[]\\l\n" +
						"+minSizeSet : boolean\\l\n" +
						"+acc : java.security.AccessControlContext\\l\n" +
						"+isAddNotifyComplete : boolean\\l\n" +
						"+isPacked : boolean\\l\n" +
						"+keyListenerK : java.lang.String\\l\n" +
						"+componentSerializedDataVersion : int\\l\n" +
						"+RIGHT_ALIGNMENT : float\\l\n" +
						"+componentOrientation : java.awt.ComponentOrientation\\l\n" +
						"+hierarchyBoundsListenerK : java.lang.String\\l\n" +
						"+coalesceEventsParams : java.lang.Class[]\\l\n" +
						"+valid : boolean\\l\n" +
						"+objectLock : java.lang.Object\\l\n" +
						"+mouseWheelListener : java.awt.event.MouseWheelListener\\l\n" +
						"+focusListenerK : java.lang.String\\l\n" +
						"+inputMethodListener : java.awt.event.InputMethodListener\\l\n" +
						"+ignoreRepaint : boolean\\l\n" +
						"+keyListener : java.awt.event.KeyListener\\l\n" +
						"+log : sun.util.logging.PlatformLogger\\l\n" +
						"|+ setAutoFocusTransferOnDisposalboolean  : void\\l\n" +
						"+ getIgnoreRepaint : boolean\\l\n" +
						"+ getTraversalRoot : java.awt.Container\\l\n" +
						"+ firePropertyChangejava.lang.String java.lang.Object java.lang.Object  : void\\l\n" +
						"+ getLocationOnScreen_NoTreeLock : java.awt.Point\\l\n" +
						"+ reshapeint int int int  : void\\l\n" +
						"+ checkCoalescing : boolean\\l\n" +
						"+ removeNotify : void\\l\n" +
						"+ checkTreeLock : void\\l\n" +
						"+ setMinimumSizejava.awt.Dimension  : void\\l\n" +
						"+ setFocusTraversalKeys_NoIDCheckint java.util.Set  : void\\l\n" +
						"+ transferFocusBackwardboolean  : boolean\\l\n" +
						"+ writeObjectjava.io.ObjectOutputStream  : void\\l\n" +
						"+ getBoundsjava.awt.Rectangle  : java.awt.Rectangle\\l\n" +
						"+ getMaximumSize : java.awt.Dimension\\l\n" +
						"+ access$500java.lang.Class  : boolean\\l\n" +
						"+ getColorModel : java.awt.image.ColorModel\\l\n" +
						"+ isAutoFocusTransferOnDisposal : boolean\\l\n" +
						"+ setBoundsint int int int  : void\\l\n" +
						"+ processHierarchyEventjava.awt.event.HierarchyEvent  : void\\l\n" +
						"+ mixOnShowing : void\\l\n" +
						"+ initIDs : void\\l\n" +
						"+ areFocusTraversalKeysSetint  : boolean\\l\n" +
						"+ resizeint int  : void\\l\n" +
						"+ firePropertyChangejava.lang.String boolean boolean  : void\\l\n" +
						"+ addHierarchyListenerjava.awt.event.HierarchyListener  : void\\l\n" +
						"+ invalidateParent : void\\l\n" +
						"+ setCursorjava.awt.Cursor  : void\\l\n" +
						"+ addMouseWheelListenerjava.awt.event.MouseWheelListener  : void\\l\n" +
						"+ paintAlljava.awt.Graphics  : void\\l\n" +
						"+ lostFocusjava.awt.Event java.lang.Object  : boolean\\l\n" +
						"+ isCoalescingEnabled : boolean\\l\n" +
						"+ coalesceEventsjava.awt.AWTEvent java.awt.AWTEvent  : java.awt.AWTEvent\\l\n" +
						"+ calculateCurrentShape : sun.java2d.pipe.Region\\l\n" +
						"+ removeFocusListenerjava.awt.event.FocusListener  : void\\l\n" +
						"+ setLocationint int  : void\\l\n" +
						"+ printAlljava.awt.Graphics  : void\\l\n" +
						"+ addMouseListenerjava.awt.event.MouseListener  : void\\l\n" +
						"+ getInputMethodRequests : java.awt.im.InputMethodRequests\\l\n" +
						"+ removejava.awt.MenuComponent  : void\\l\n" +
						"+ notifyNewBoundsboolean boolean  : void\\l\n" +
						"+ getInputMethodListeners : java.awt.event.InputMethodListener[]\\l\n" +
						"+ getComponentAtjava.awt.Point  : java.awt.Component\\l\n" +
						"+ checkImagejava.awt.Image int int java.awt.image.ImageObserver  : int\\l\n" +
						"+ removeMouseListenerjava.awt.event.MouseListener  : void\\l\n" +
						"+ getSiblingIndexAbove : int\\l\n" +
						"+ processHierarchyBoundsEventjava.awt.event.HierarchyEvent  : void\\l\n" +
						"+ mouseDragjava.awt.Event int int  : boolean\\l\n" +
						"+ revalidateSynchronously : void\\l\n" +
						"+ getPropertyChangeListeners : java.beans.PropertyChangeListener[]\\l\n" +
						"+ transferFocusboolean  : boolean\\l\n" +
						"+ actionjava.awt.Event java.lang.Object  : boolean\\l\n" +
						"+ invalidateIfValid : void\\l\n" +
						"+ mouseUpjava.awt.Event int int  : boolean\\l\n" +
						"+ repaintParentIfNeededint int int int  : void\\l\n" +
						"+ getFocusTraversalKeysint  : java.util.Set\\l\n" +
						"+ firePropertyChangejava.lang.String char char  : void\\l\n" +
						"+ checkImagejava.awt.Image java.awt.image.ImageObserver  : int\\l\n" +
						"+ setVisibleboolean  : void\\l\n" +
						"+ preferredSize : java.awt.Dimension\\l\n" +
						"+ list : void\\l\n" +
						"+ processMouseEventjava.awt.event.MouseEvent  : void\\l\n" +
						"+ hasFocus : boolean\\l\n" +
						"+ getPeer : java.awt.peer.ComponentPeer\\l\n" +
						"+ getHeight : int\\l\n" +
						"+ getAccessibleContext : javax.accessibility.AccessibleContext\\l\n" +
						"+ getSizejava.awt.Dimension  : java.awt.Dimension\\l\n" +
						"+ nextFocus : void\\l\n" +
						"+ getFontMetricsjava.awt.Font  : java.awt.FontMetrics\\l\n" +
						"+ access$400java.awt.Component  : java.awt.Insets\\l\n" +
						"+ subtractAndApplyShapeBelowMe : void\\l\n" +
						"+ setLocalejava.util.Locale  : void\\l\n" +
						"+ doLayout : void\\l\n" +
						"+ createVolatileImageint int  : java.awt.image.VolatileImage\\l\n" +
						"+ isValid : boolean\\l\n" +
						"+ mouseExitjava.awt.Event int int  : boolean\\l\n" +
						"+ getAccessibleStateSet : javax.accessibility.AccessibleStateSet\\l\n" +
						"+ getParent_NoClientCode : java.awt.Container\\l\n" +
						"+ getFont_NoClientCode : java.awt.Font\\l\n" +
						"+ setSizejava.awt.Dimension  : void\\l\n" +
						"+ locateint int  : java.awt.Component\\l\n" +
						"+ firePropertyChangejava.lang.String double double  : void\\l\n" +
						"+ readObjectjava.io.ObjectInputStream  : void\\l\n" +
						"+ setPreferredSizejava.awt.Dimension  : void\\l\n" +
						"+ isMixingNeeded : boolean\\l\n" +
						"+ addKeyListenerjava.awt.event.KeyListener  : void\\l\n" +
						"+ firePropertyChangejava.lang.String int int  : void\\l\n" +
						"+ getNormalShape : sun.java2d.pipe.Region\\l\n" +
						"+ removeInputMethodListenerjava.awt.event.InputMethodListener  : void\\l\n" +
						"+ isFocusTraversable : boolean\\l\n" +
						"+ getToolkitImpl : java.awt.Toolkit\\l\n" +
						"+ updatejava.awt.Graphics  : void\\l\n" +
						"+ getParent : java.awt.Container\\l\n" +
						"+ isMaximumSizeSet : boolean\\l\n" +
						"+ updateGraphicsDatajava.awt.GraphicsConfiguration  : boolean\\l\n" +
						"+ showboolean  : void\\l\n" +
						"+ access$100java.awt.Component  : java.awt.Point\\l\n" +
						"+ removePropertyChangeListenerjava.beans.PropertyChangeListener  : void\\l\n" +
						"+ firePropertyChangejava.lang.String long long  : void\\l\n" +
						"+ isForegroundSet : boolean\\l\n" +
						"+ getPropertyChangeListenersjava.lang.String  : java.beans.PropertyChangeListener[]\\l\n" +
						"+ getMouseListeners : java.awt.event.MouseListener[]\\l\n" +
						"+ dispatchEventjava.awt.AWTEvent  : void\\l\n" +
						"+ isNonOpaqueForMixing : boolean\\l\n" +
						"+ addComponentListenerjava.awt.event.ComponentListener  : void\\l\n" +
						"+ repaintlong int int int int  : void\\l\n" +
						"+ containsint int  : boolean\\l\n" +
						"+ dispatchMouseWheelToAncestorjava.awt.event.MouseWheelEvent  : boolean\\l\n" +
						"+ getPreferredSize : java.awt.Dimension\\l\n" +
						"+ updateZOrder : void\\l\n" +
						"+ createImageint int  : java.awt.Image\\l\n" +
						"+ prepareImagejava.awt.Image java.awt.image.ImageObserver  : boolean\\l\n" +
						"+ layout : void\\l\n" +
						"+ setFocusTraversalKeysint java.util.Set  : void\\l\n" +
						"+ getHWPeerAboveMe : java.awt.peer.ComponentPeer\\l\n" +
						"+ getGraphicsConfiguration_NoClientCode : java.awt.GraphicsConfiguration\\l\n" +
						"+ processFocusEventjava.awt.event.FocusEvent  : void\\l\n" +
						"+ getAccessibleIndexInParent : int\\l\n" +
						"+ getMousePosition : java.awt.Point\\l\n" +
						"+ paintHeavyweightComponentsjava.awt.Graphics  : void\\l\n" +
						"+ addPropertyChangeListenerjava.beans.PropertyChangeListener  : void\\l\n" +
						"+ removeComponentListenerjava.awt.event.ComponentListener  : void\\l\n" +
						"+ containsFocus : boolean\\l\n" +
						"+ hide : void\\l\n" +
						"+ mixOnZOrderChangingint int  : void\\l\n" +
						"+ isCursorSet : boolean\\l\n" +
						"+ paintjava.awt.Graphics  : void\\l\n" +
						"+ getHierarchyListeners : java.awt.event.HierarchyListener[]\\l\n" +
						"+ createImagejava.awt.image.ImageProducer  : java.awt.Image\\l\n" +
						"+ getBackBuffer : java.awt.Image\\l\n" +
						"+ addFocusListenerjava.awt.event.FocusListener  : void\\l\n" +
						"+ getBackground : java.awt.Color\\l\n" +
						"+ prepareImagejava.awt.Image int int java.awt.image.ImageObserver  : boolean\\l\n" +
						"+ mouseEnterjava.awt.Event int int  : boolean\\l\n" +
						"+ repaint : void\\l\n" +
						"+ getForeground : java.awt.Color\\l\n" +
						"+ findUnderMouseInWindowjava.awt.PointerInfo  : java.awt.Component\\l\n" +
						"+ checkGDjava.lang.String  : void\\l\n" +
						"+ setDropTargetjava.awt.dnd.DropTarget  : void\\l\n" +
						"+ setMaximumSizejava.awt.Dimension  : void\\l\n" +
						"+ disableEventslong  : void\\l\n" +
						"+ enableInputMethodsboolean  : void\\l\n" +
						"+ location_NoClientCode : java.awt.Point\\l\n" +
						"+ getTreeLock : java.lang.Object\\l\n" +
						"+ addjava.awt.PopupMenu  : void\\l\n" +
						"+ getToolkit : java.awt.Toolkit\\l\n" +
						"+ getFocusCycleRootAncestor : java.awt.Container\\l\n" +
						"+ setNamejava.lang.String  : void\\l\n" +
						"+ getListenersjava.lang.Class  : java.util.EventListener[]\\l\n" +
						"+ location : java.awt.Point\\l\n" +
						"+ minimumSize : java.awt.Dimension\\l\n" +
						"+ listjava.io.PrintStream  : void\\l\n" +
						"+ revalidate : void\\l\n" +
						"+ checkWindowClosingException : boolean\\l\n" +
						"+ mouseDownjava.awt.Event int int  : boolean\\l\n" +
						"+ processEventjava.awt.AWTEvent  : void\\l\n" +
						"+ removeMouseWheelListenerjava.awt.event.MouseWheelListener  : void\\l\n" +
						"+ enableEventslong  : void\\l\n" +
						"+ getGraphicsConfiguration : java.awt.GraphicsConfiguration\\l\n" +
						"+ printHeavyweightComponentsjava.awt.Graphics  : void\\l\n" +
						"+ show : void\\l\n" +
						"+ getHierarchyBoundsListeners : java.awt.event.HierarchyBoundsListener[]\\l\n" +
						"+ gotFocusjava.awt.Event java.lang.Object  : boolean\\l\n" +
						"+ reshapeNativePeerint int int int int  : void\\l\n" +
						"+ clearMostRecentFocusOwnerOnHide : void\\l\n" +
						"+ imageUpdatejava.awt.Image int int int int int  : boolean\\l\n" +
						"+ setComponentOrientationjava.awt.ComponentOrientation  : void\\l\n" +
						"+ listjava.io.PrintStream int  : void\\l\n" +
						"+ setForegroundjava.awt.Color  : void\\l\n" +
						"+ createBufferStrategyint java.awt.BufferCapabilities  : void\\l\n" +
						"+ countHierarchyMembers : int\\l\n" +
						"+ removeHierarchyListenerjava.awt.event.HierarchyListener  : void\\l\n" +
						"+ setBoundsOpint  : void\\l\n" +
						"+ getInputContext : java.awt.im.InputContext\\l\n" +
						"+ isFocusTraversableOverridden : boolean\\l\n" +
						"+ requestFocusInWindowsun.awt.CausedFocusEvent$Cause  : boolean\\l\n" +
						"+ printjava.awt.Graphics  : void\\l\n" +
						"+ getBufferStrategy : java.awt.image.BufferStrategy\\l\n" +
						"+ deliverEventjava.awt.Event  : void\\l\n" +
						"+ isVisible : boolean\\l\n" +
						"+ handleEventjava.awt.Event  : boolean\\l\n" +
						"+ removeMouseMotionListenerjava.awt.event.MouseMotionListener  : void\\l\n" +
						"+ listjava.io.PrintWriter int  : void\\l\n" +
						"+ repaintint int int int  : void\\l\n" +
						"+ isDoubleBuffered : boolean\\l\n" +
						"+ updateCursorImmediately : void\\l\n" +
						"+ isPreferredSizeSet : boolean\\l\n" +
						"+ removeHierarchyBoundsListenerjava.awt.event.HierarchyBoundsListener  : void\\l\n" +
						"+ getBoundsOp : int\\l\n" +
						"+ applyComponentOrientationjava.awt.ComponentOrientation  : void\\l\n" +
						"+ getCursor_NoClientCode : java.awt.Cursor\\l\n" +
						"+ getMouseWheelListeners : java.awt.event.MouseWheelListener[]\\l\n" +
						"+ addPropertyChangeListenerjava.lang.String java.beans.PropertyChangeListener  : void\\l\n" +
						"+ processComponentEventjava.awt.event.ComponentEvent  : void\\l\n" +
						"+ applyCurrentShapeBelowMe : void\\l\n" +
						"+ setEnabledboolean  : void\\l\n" +
						"+ firePropertyChangejava.lang.String byte byte  : void\\l\n" +
						"+ validate : void\\l\n" +
						"+ getContainingWindow : java.awt.Window\\l\n" +
						"+ setGraphicsConfigurationjava.awt.GraphicsConfiguration  : void\\l\n" +
						"+ requestFocusHelperboolean boolean sun.awt.CausedFocusEvent$Cause  : boolean\\l\n" +
						"+ areInputMethodsEnabled : boolean\\l\n" +
						"+ addHierarchyBoundsListenerjava.awt.event.HierarchyBoundsListener  : void\\l\n" +
						"+ getLocationOnWindow : java.awt.Point\\l\n" +
						"+ isLightweight : boolean\\l\n" +
						"+ setIgnoreRepaintboolean  : void\\l\n" +
						"+ access$002java.awt.Component sun.java2d.pipe.Region  : sun.java2d.pipe.Region\\l\n" +
						"+ getDropTarget : java.awt.dnd.DropTarget\\l\n" +
						"+ resizejava.awt.Dimension  : void\\l\n" +
						"+ getInsets_NoClientCode : java.awt.Insets\\l\n" +
						"+ getLocationOnScreen : java.awt.Point\\l\n" +
						"+ getBaselineint int  : int\\l\n" +
						"+ postEventjava.awt.Event  : boolean\\l\n" +
						"+ applyCurrentShape : void\\l\n" +
						"+ isRecursivelyVisible : boolean\\l\n" +
						"+ getMouseMotionListeners : java.awt.event.MouseMotionListener[]\\l\n" +
						"+ getGraphics_NoClientCode : java.awt.Graphics\\l\n" +
						"+ paramString : java.lang.String\\l\n" +
						"+ isBackgroundSet : boolean\\l\n" +
						"+ getLocale : java.util.Locale\\l\n" +
						"+ getBaselineResizeBehavior : java.awt.Component$BaselineResizeBehavior\\l\n" +
						"+ keyUpjava.awt.Event int  : boolean\\l\n" +
						"+ setFocusableboolean  : void\\l\n" +
						"+ getComponentOrientation : java.awt.ComponentOrientation\\l\n" +
						"+ getOpaqueShape : sun.java2d.pipe.Region\\l\n" +
						"+ isMinimumSizeSet : boolean\\l\n" +
						"+ getWidth : int\\l\n" +
						"+ removeKeyListenerjava.awt.event.KeyListener  : void\\l\n" +
						"+ postsOldMouseEvents : boolean\\l\n" +
						"+ isFocusable : boolean\\l\n" +
						"+ getFocusTraversalKeysEnabled : boolean\\l\n" +
						"+ requestFocusInWindowboolean sun.awt.CausedFocusEvent$Cause  : boolean\\l\n" +
						"+ getAppliedShape : sun.java2d.pipe.Region\\l\n" +
						"+ listjava.io.PrintWriter  : void\\l\n" +
						"+ relocateComponent : void\\l\n" +
						"+ mixOnHidingboolean  : void\\l\n" +
						"+ clearCurrentFocusCycleRootOnHide : void\\l\n" +
						"+ canBeFocusOwner : boolean\\l\n" +
						"+ lightweightPaintjava.awt.Graphics  : void\\l\n" +
						"+ processMouseWheelEventjava.awt.event.MouseWheelEvent  : void\\l\n" +
						"+ setLocationjava.awt.Point  : void\\l\n" +
						"+ getRecursivelyVisibleBounds : java.awt.Rectangle\\l\n" +
						"+ isEnabledImpl : boolean\\l\n" +
						"+ isVisible_NoClientCode : boolean\\l\n" +
						"+ getComponentAtint int  : java.awt.Component\\l\n" +
						"+ getY : int\\l\n" +
						"+ firePropertyChangejava.lang.String short short  : void\\l\n" +
						"+ getX : int\\l\n" +
						"+ isFontSet : boolean\\l\n" +
						"+ toString : java.lang.String\\l\n" +
						"+ constructComponentName : java.lang.String\\l\n" +
						"+ getLocation : java.awt.Point\\l\n" +
						"+ transferFocus : void\\l\n" +
						"+ canBeFocusOwnerRecursively : boolean\\l\n" +
						"+ getAlignmentY : float\\l\n" +
						"+ setRequestFocusControllersun.awt.RequestFocusController  : void\\l\n" +
						"+ applyCompoundShapesun.java2d.pipe.Region  : void\\l\n" +
						"+ getAlignmentX : float\\l\n" +
						"+ getSiblingIndexBelow : int\\l\n" +
						"+ setFocusTraversalKeysEnabledboolean  : void\\l\n" +
						"+ lightweightPrintjava.awt.Graphics  : void\\l\n" +
						"+ getLocationjava.awt.Point  : java.awt.Point\\l\n" +
						"+ isCoalesceEventsOverridenjava.lang.Class  : boolean\\l\n" +
						"+ processKeyEventjava.awt.event.KeyEvent  : void\\l\n" +
						"+ initializeFocusTraversalKeys : void\\l\n" +
						"+ doSwingSerialization : void\\l\n" +
						"+ getKeyListeners : java.awt.event.KeyListener[]\\l\n" +
						"+ getContainer : java.awt.Container\\l\n" +
						"+ getCursor : java.awt.Cursor\\l\n" +
						"+ requestFocus : void\\l\n" +
						"+ getComponentListeners : java.awt.event.ComponentListener[]\\l\n" +
						"+ getMinimumSize : java.awt.Dimension\\l\n" +
						"+ createBufferStrategyint  : void\\l\n" +
						"+ addInputMethodListenerjava.awt.event.InputMethodListener  : void\\l\n" +
						"+ getName : java.lang.String\\l\n" +
						"+ dispatchEventImpljava.awt.AWTEvent  : void\\l\n" +
						"+ moveint int  : void\\l\n" +
						"+ addNotify : void\\l\n" +
						"+ disable : void\\l\n" +
						"+ bounds : java.awt.Rectangle\\l\n" +
						"+ eventTypeEnabledint  : boolean\\l\n" +
						"+ requestFocusInWindowboolean  : boolean\\l\n" +
						"+ getObjectLock : java.lang.Object\\l\n" +
						"+ requestFocusInWindow : boolean\\l\n" +
						"+ createVolatileImageint int java.awt.ImageCapabilities  : java.awt.image.VolatileImage\\l\n" +
						"+ isRequestFocusAcceptedboolean boolean sun.awt.CausedFocusEvent$Cause  : boolean\\l\n" +
						"+ transferFocusUpCycle : void\\l\n" +
						"+ isInstanceOfjava.lang.Object java.lang.String  : boolean\\l\n" +
						"+ numListeninglong  : int\\l\n" +
						"+ isEnabled : boolean\\l\n" +
						"+ getSize : java.awt.Dimension\\l\n" +
						"+ processInputMethodEventjava.awt.event.InputMethodEvent  : void\\l\n" +
						"+ isDisplayable : boolean\\l\n" +
						"+ addMouseMotionListenerjava.awt.event.MouseMotionListener  : void\\l\n" +
						"+ requestFocussun.awt.CausedFocusEvent$Cause  : boolean\\l\n" +
						"+ transferFocusBackward : void\\l\n" +
						"+ getBounds : java.awt.Rectangle\\l\n" +
						"+ keyDownjava.awt.Event int  : boolean\\l\n" +
						"+ areBoundsValid : boolean\\l\n" +
						"+ adjustListeningChildrenOnParentlong int  : void\\l\n" +
						"+ containsjava.awt.Point  : boolean\\l\n" +
						"+ getFocusListeners : java.awt.event.FocusListener[]\\l\n" +
						"+ getNextFocusCandidate : java.awt.Component\\l\n" +
						"+ getGraphics : java.awt.Graphics\\l\n" +
						"+ insideint int  : boolean\\l\n" +
						"+ eventEnabledjava.awt.AWTEvent  : boolean\\l\n" +
						"+ getFocusTraversalKeys_NoIDCheckint  : java.util.Set\\l\n" +
						"+ enable : void\\l\n" +
						"+ repaintlong  : void\\l\n" +
						"+ invalidate : void\\l\n" +
						"+ mouseMovejava.awt.Event int int  : boolean\\l\n" +
						"+ removePropertyChangeListenerjava.lang.String java.beans.PropertyChangeListener  : void\\l\n" +
						"+ requestFocusHelperboolean boolean  : boolean\\l\n" +
						"+ pointRelativeToComponentjava.awt.Point  : java.awt.Point\\l\n" +
						"+ setFontjava.awt.Font  : void\\l\n" +
						"+ isOpaque : boolean\\l\n" +
						"+ isShowing : boolean\\l\n" +
						"+ firePropertyChangejava.lang.String float float  : void\\l\n" +
						"+ enableboolean  : void\\l\n" +
						"+ requestFocusboolean sun.awt.CausedFocusEvent$Cause  : boolean\\l\n" +
						"+ requestFocusboolean  : boolean\\l\n" +
						"+ subtractAndApplyShapesun.java2d.pipe.Region  : void\\l\n" +
						"+ createHierarchyEventsint java.awt.Component java.awt.Container long boolean  : int\\l\n" +
						"+ getNativeContainer : java.awt.Container\\l\n" +
						"+ setBackgroundjava.awt.Color  : void\\l\n" +
						"+ setBoundsjava.awt.Rectangle  : void\\l\n" +
						"+ setSizeint int  : void\\l\n" +
						"+ autoProcessMouseWheeljava.awt.event.MouseWheelEvent  : void\\l\n" +
						"+ mixOnValidating : void\\l\n" +
						"+ getAccessControlContext : java.security.AccessControlContext\\l\n" +
						"+ isFocusCycleRootjava.awt.Container  : boolean\\l\n" +
						"+ isSameOrAncestorOfjava.awt.Component boolean  : boolean\\l\n" +
						"+ mixOnReshaping : void\\l\n" +
						"+ getFont : java.awt.Font\\l\n" +
						"+ size : java.awt.Dimension\\l\n" +
						"+ processMouseMotionEventjava.awt.event.MouseEvent  : void\\l\n" +
						"+ isFocusOwner : boolean\\l\n" +
						"}\"]edge [ arrowhead = \"empty\" style = \"dotted\"]\n" +
						"edge [ style = \"dotted\" arrowhead = \"open\"]\n" +
						"Component -> Container\n" +
						"Component -> Component\n" +
						"Button [color = \"yellow\" label = \"{Button\\n\\<\\<Leaf\\>\\>|+serialVersionUID : long\\l\n" +
						"+label : java.lang.String\\l\n" +
						"+actionListener : java.awt.event.ActionListener\\l\n" +
						"+actionCommand : java.lang.String\\l\n" +
						"+base : java.lang.String\\l\n" +
						"+buttonSerializedDataVersion : int\\l\n" +
						"+nameCounter : int\\l\n" +
						"|+ setActionCommandjava.lang.String  : void\\l\n" +
						"+ getListenersjava.lang.Class  : java.util.EventListener[]\\l\n" +
						"+ removeActionListenerjava.awt.event.ActionListener  : void\\l\n" +
						"+ constructComponentName : java.lang.String\\l\n" +
						"+ getActionCommand : java.lang.String\\l\n" +
						"+ addActionListenerjava.awt.event.ActionListener  : void\\l\n" +
						"+ addNotify : void\\l\n" +
						"+ paramString : java.lang.String\\l\n" +
						"+ writeObjectjava.io.ObjectOutputStream  : void\\l\n" +
						"+ processActionEventjava.awt.event.ActionEvent  : void\\l\n" +
						"+ eventEnabledjava.awt.AWTEvent  : boolean\\l\n" +
						"+ processEventjava.awt.AWTEvent  : void\\l\n" +
						"+ readObjectjava.io.ObjectInputStream  : void\\l\n" +
						"+ getAccessibleContext : javax.accessibility.AccessibleContext\\l\n" +
						"+ setLabeljava.lang.String  : void\\l\n" +
						"+ initIDs : void\\l\n" +
						"+ getActionListeners : java.awt.event.ActionListener[]\\l\n" +
						"+ getLabel : java.lang.String\\l\n" +
						"}\"]edge [ style = \"normal\", arrowhead = \"normal\"]\n" +
						"Button -> Component\n" +
						"edge [ arrowhead = \"empty\" style = \"dotted\"]\n" +
						"}");
	}	

	public void assertComposite(String[] classNames, String[] componentClasses, String[] compositeClasses,
			String[] leafClasses, String[] noPatternClasses, String expectedUml) {
		try {
			assertEquals(classNames.length,
					compositeClasses.length + componentClasses.length + noPatternClasses.length + leafClasses.length);
			UmlWrapper umlWrapper = new UmlWrapper(classNames);
			umlWrapper.addBuilderClass(ExtensionBuilder.class);
			umlWrapper.addBuilderClass(ImplementsBuilder.class);
			umlWrapper.addBuilderClass(UsesBuilder.class);
			umlWrapper.addBuilderClass(CompositeBuilder.class);
			ArrayList<IPhase> phases = new ArrayList<IPhase>();
			phases.add(new Load(umlWrapper));
			phases.add(new PatternDetection(umlWrapper));
			phases.add(new GenerateUML(umlWrapper));
			for(IPhase p : phases) {
				p.execute();
			}
			String actualUml = umlWrapper.getUmlString();
			HashMap<String, IClassRecord> recordMap = umlWrapper.getRecords();

			for (String className : compositeClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				assertTrue(record.getPatternNames().contains("Composite"));
				assertFalse(record.getPatternNames().contains("Component"));
				assertFalse(record.getPatternNames().contains("Leaf"));
			}

			for (String className : componentClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				assertFalse(record.getPatternNames().contains("Composite"));
				assertTrue(record.getPatternNames().contains("Component"));
				assertFalse(record.getPatternNames().contains("Leaf"));
			}

			for (String className : leafClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				assertFalse(record.getPatternNames().contains("Composite"));
				assertFalse(record.getPatternNames().contains("Component"));
				assertTrue(record.getPatternNames().contains("Leaf"));
			}

			for (String className : noPatternClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				assertEquals(0, record.getPatternNames().size());
			}
			if (expectedUml.equals("")) {
				UmlWrapper baseUmlWrapper = new UmlWrapper(classNames);
				baseUmlWrapper.addBuilderClass(ExtensionBuilder.class);
				baseUmlWrapper.addBuilderClass(ImplementsBuilder.class);
				baseUmlWrapper.addBuilderClass(UsesBuilder.class);
//				assertEquals(baseUmlWrapper.load(), actualUml);
				baseUmlWrapper.load();
			} else {
				assertEquals(expectedUml, actualUml);
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			fail();
		}
	}
}
