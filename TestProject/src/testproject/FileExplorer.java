package testproject;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;



/**
 * Creates graphical File Explorer where individual filenames in directories are
 * displayed. Adapted from Core Java Foundation Classes by Kim Topley.
 * <P>
 * http://www.lmbrown.com/. &copy; 2005 Larry Brown; may be freely used or
 * adapted.
 * 
 * To Do: platform independence
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  (C) 2007 Marty Hall, Larry Brown, and Yaakov Chaikin;
 *  may be freely used or adapted.
 */

public class FileExplorer extends JTree
{
   public FileExplorer(String path)
      throws FileNotFoundException, SecurityException
   {  
      this.setFont(new Font("Dialog", Font.BOLD, 13));
      URL imgURL = FileExplorer.class.getResource("fileIcon.gif");
      if (imgURL == null) System.out.println("IMAGE NOT FOUND!");
      ImageIcon leafIcon = new ImageIcon(imgURL);
      DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer();
      cellRenderer.setLeafIcon(leafIcon);
      this.setCellRenderer(cellRenderer);

      // Use horizontal and vertical lines
      putClientProperty("JTree.lineStyle", "Angled");

      // Create the first node
      String name = path.substring(path.lastIndexOf("\\"));
      File f = new File(path.substring(0, path.lastIndexOf("\\")));
      FileExplorerNode rootNode = new FileExplorerNode(f, name);

      // Populate the root node with its subdirectories
      boolean addedNodes = rootNode.populateDirectories(true);
      setModel(new DefaultTreeModel(rootNode));
      
      // Listen for Tree Selection Events
      addTreeExpansionListener(new TreeExpansionHandler());
   }


   // Returns the full pathname for a path, or null if not a known path
   public String getPathName(TreePath path)
   {
      Object o = path.getLastPathComponent();
      if (o instanceof FileExplorerNode)
      {
         return ((FileExplorerNode) o).file.getAbsolutePath();
      }
      return null;
   }


   // Returns the File for a path, or null if not a known path
   public File getFile(TreePath path)
   {
      Object o = path.getLastPathComponent();
      if (o instanceof FileExplorerNode)
      {
         return ((FileExplorerNode) o).file;
      }
      return null;
   }


   public static void main(String[] args)
   {
      WindowUtilities.setNativeLookAndFeel();
      
      try
      {
         if (args.length != 2)
         {
            System.out.println("Please supply one initial directory and title");
            System.exit(0);
         }
         
         final FileExplorer ft = new FileExplorer(args[0]);
         ft.addTreeSelectionListener(new TreeSelectionListener()
         {
            public void valueChanged(TreeSelectionEvent evt)
            {
               TreePath path = evt.getPath();
               String name = ft.getPathName(path);
               File file = ft.getFile(path);
               System.out.println("File " + name + " has been "
                     + (evt.isAddedPath() ? "selected" : "deselected"));
               System.out.println("File object is " + file);
            }
         });
         
         // Create JFrame and set bigger font
         JFrame f = new JFrame(args[1]);
         f.setFont(new Font("Dialog", Font.BOLD, 14));
         JScrollPane scrollPane = new JScrollPane(ft);
         scrollPane.setMinimumSize(new Dimension(500,400));
         f.getContentPane().add(scrollPane);
         f.setSize(300, 300);
         f.addWindowListener(new WindowAdapter()
         {
            public void windowClosing(WindowEvent evt)
            {
               System.exit(0);
            }
         });
         f.setVisible(true);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File " + args[0] + " not found");
      }
   }

   // Inner class that represents a node in this file system tree
   protected static class FileExplorerNode extends DefaultMutableTreeNode
   {
      public FileExplorerNode(File parent, String name)
         throws SecurityException, FileNotFoundException
      {

         this.name = name;

         // See if this node exists and whether it is a directory
         file = new File(parent, name);
         if (!file.exists())
         {
            throw new FileNotFoundException("File " + name + " does not exist");
         }

         isDir = file.isDirectory();

         // Hold the File as the user object.
         setUserObject(file);
      }


      // Override isLeaf to check whether this is a directory
      public boolean isLeaf()
      {
         return !isDir;
      }


      // Override getAllowsChildren to check whether this is a directory
      public boolean getAllowsChildren()
      {
         return true;
      }


      public String toString()
      {
         return name;
      }


      // If we are a directory, scan our contents and populate
      // with children. In addition, populate those children
      // if the "descend" flag is true. We only descend once,
      // to avoid recursing the whole subtree.
      // Returns true if some nodes were added
      boolean populateDirectories(boolean descend)
      {
         boolean addedNodes = false;

         // Do this only once
         if (populated == false)
         {
            if (interim == true)
            {
               // We have had a quick look here before:
               // remove the dummy node that we added last time
               removeAllChildren();
               interim = false;
            }

            String[] names = file.list(); // Get list of contents

            // Process the directories
            for (int i = 0; i < names.length; i++)
            {
               String name = names[i];
               File d = new File(file, name);
               try
               {
                  //if (d.isDirectory()) {
                  if (true)
                  {
                     FileExplorerNode node = new FileExplorerNode(file, name);
                     this.add(node);
                     //if (descend) {
                     //  node.populateDirectories(false);
                     //}
                     node.populateDirectories(true);

                     addedNodes = true;
                     if (descend == false)
                     {
                        // Only add one node if not descending
                        break;
                     }
                  }
               }
               catch (Throwable t)
               {
                  // Ignore phantoms or access problems
               }
            }

            // If we were scanning to get all subdirectories,
            // or if we found no subdirectories, there is no
            // reason to look at this directory again, so
            // set populated to true. Otherwise, we set interim
            // so that we look again in the future if we need to
            if (descend == true || addedNodes == false)
            {
               populated = true;
            }
            else
            {
               // Just set interim state
               interim = true;
            }
         }

         return addedNodes;
      }

      protected File file; // File object for this node

      protected String name; // Name of this node

      protected boolean populated;// true if we have been populated

      protected boolean interim; // true if we are in interim state

      protected boolean isDir; // true if this is a directory
   }

   // Inner class that handles Tree Expansion Events
   protected class TreeExpansionHandler implements TreeExpansionListener
   {
      public void treeExpanded(TreeExpansionEvent evt)
      {
         TreePath path = evt.getPath(); // The expanded path
         JTree tree = (JTree) evt.getSource(); // The tree

         // Get the last component of the path and
         // arrange to have it fully populated.
         FileExplorerNode node = (FileExplorerNode) path.getLastPathComponent();
         if (node.populateDirectories(true))
         {
            ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
         }
      }


      public void treeCollapsed(TreeExpansionEvent evt)
      {
         // Nothing to do
      }
   }
}