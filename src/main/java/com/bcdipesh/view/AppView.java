/*
 * Copyright (c) 2020, Dipesh B.C.. All rights reserved.
 * Unauthorized copying of this file, via any medium is
 * strictly prohibited.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package main.java.com.bcdipesh.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;

/**
 * Represents the View Component of the application.
 *
 * @author Dipesh B.C.
 * @version 2.1
 */
@SuppressWarnings("serial")
public class AppView extends JFrame {

  // ... Components for the view.
  private final JMenuItem menuItemLoadFromFile;
  private final JMenuItem menuItemLoadFromDir;
  private final JMenuItem menuItemAbout;
  private final JMenuItem menuItemExit;

  private final JLabel loadDataFromFileLabel;
  private final JLabel loadDataFromDirLabel;
  private final JLabel loadPatternLabel;

  private final JTextArea searchResults;

  private final JButton loadDataFromFileBtn;
  private final JButton loadDataFromDirBtn;
  private final JButton searchPatternBtn;
  private final JButton loadPatternBtn;

  /** Creates a view for the application, using the components. */
  public AppView() {

    // ... Set custom look and feel.
    setLookAndFeel();

    // ... Initialize components.

    // ... Get resource URL
    final URL fileIconURL = AppView.class.getResource("/main/resources/icons/file.png");
    final URL dirIconURL = AppView.class.getResource("/main/resources/icons/search.png");
    final URL aboutIconURL = AppView.class.getResource("/main/resources/icons/about.png");
    final URL exitIconURL = AppView.class.getResource("/main/resources/icons/close.png");
    final URL patternIconURL = AppView.class.getResource("/main/resources/icons/pattern.png");
    final URL searchIconURL = AppView.class.getResource("/main/resources/icons/search.png");

    // ... JPanel
    final JPanel westPanel = new JPanel();
    final JPanel westNorthPanel = new JPanel();
    final JPanel westSouthPanel = new JPanel();
    final JPanel centerPanel = new JPanel();

    // ... ImageIcon
    final Icon fileIcon = new ImageIcon(fileIconURL);
    final Icon dirIcon = new ImageIcon(dirIconURL);
    final Icon aboutIcon = new ImageIcon(aboutIconURL);
    final Icon exitIcon = new ImageIcon(exitIconURL);
    final Icon patternIcon = new ImageIcon(patternIconURL);
    final Icon searchIcon = new ImageIcon(searchIconURL);

    // ... JMenuItem
    menuItemLoadFromFile = new JMenuItem("Load from file");
    menuItemLoadFromDir = new JMenuItem("Load from dir");
    menuItemAbout = new JMenuItem("About");
    menuItemExit = new JMenuItem("Exit");

    menuItemLoadFromFile.setIcon(fileIcon);
    menuItemLoadFromDir.setIcon(dirIcon);
    menuItemAbout.setIcon(aboutIcon);
    menuItemExit.setIcon(exitIcon);

    // ... JMenu
    final JMenu fileMenu = new JMenu("File");
    fileMenu.add(menuItemLoadFromFile);
    fileMenu.add(menuItemLoadFromDir);
    fileMenu.add(menuItemAbout);
    fileMenu.add(menuItemExit);

    fileMenu.setToolTipText("Contains list of common actions to perform");

    // ... JMenuBar
    final JMenuBar menuBar = new JMenuBar();
    menuBar.add(fileMenu);

    // ... JLabel
    loadDataFromFileLabel = new JLabel("Select a file...");
    loadDataFromDirLabel = new JLabel("Select a dir...");
    loadPatternLabel = new JLabel("Select a pattern...");

    // ... JTextArea
    searchResults = new JTextArea(10, 10);
    searchResults.setEditable(false);

    // ... JScrollPane
    final JScrollPane scrollPane = new JScrollPane(searchResults);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // ... JButton
    loadDataFromFileBtn = new JButton(fileIcon);
    loadDataFromDirBtn = new JButton(dirIcon);
    loadPatternBtn = new JButton(patternIcon);
    searchPatternBtn = new JButton(searchIcon);

    loadDataFromFileBtn.setFocusPainted(false);
    loadDataFromDirBtn.setFocusPainted(false);
    loadPatternBtn.setFocusPainted(false);
    searchPatternBtn.setFocusPainted(false);

    loadDataFromFileBtn.setToolTipText("This loads a source file to scan");
    loadDataFromDirBtn.setToolTipText("This loads a directory to scan");
    loadPatternBtn.setToolTipText("This loads a pattern file");
    searchPatternBtn.setToolTipText("This starts search for pattern");

    loadDataFromFileBtn.setMnemonic(KeyEvent.VK_F);
    loadDataFromDirBtn.setMnemonic(KeyEvent.VK_D);
    loadPatternBtn.setMnemonic(KeyEvent.VK_P);
    searchPatternBtn.setMnemonic(KeyEvent.VK_S);

    // ... Layout the components.
    westPanel.setLayout(new BorderLayout());

    westNorthPanel.add(loadDataFromFileLabel);
    westNorthPanel.add(loadDataFromFileBtn);
    westNorthPanel.add(loadDataFromDirLabel);
    westNorthPanel.add(loadDataFromDirBtn);

    westSouthPanel.add(loadPatternLabel);
    westSouthPanel.add(loadPatternBtn);
    westSouthPanel.add(searchPatternBtn);

    centerPanel.add(scrollPane);

    westPanel.add(BorderLayout.NORTH, westNorthPanel);
    westPanel.add(BorderLayout.SOUTH, westSouthPanel);

    this.setJMenuBar(menuBar);

    this.add(BorderLayout.WEST, westPanel);
    this.add(BorderLayout.CENTER, scrollPane);

    // ... Finalize the layout.
    this.setTitle("Byte Pattern Matcher");
    this.setSize(700, 300);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void setLookAndFeel() {
    try {
      JDialog.setDefaultLookAndFeelDecorated(true);
      UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  // ... Methods for registering controller's ActionListener.

  /**
   * Provides mechanism to add ActionListener to menu item load from a file. Use this function to
   * add the controller's ActionListener for listening to events when user selects menu item load
   * from a file from the file menu.
   *
   * @param menuItemLoadFromFileListener The Controller's ActionListener to listen to events from
   *     the menu item load from a file.
   */
  public void addMenuItemLoadFromFileListener(ActionListener menuItemLoadFromFileListener) {
    menuItemLoadFromFile.addActionListener(menuItemLoadFromFileListener);
  }

  /**
   * Provides mechanism to add ActionListener to menu item load from a directory. Use this function
   * to add the controller's ActionListener for listening to events when user selects menu item load
   * from a directory from the file menu.
   *
   * @param menuItemLoadFromDirListener The Controller's ActionListener object to listen to events
   *     from the menu item load from a directory.
   */
  public void addMenuItemLoadFromDirListener(ActionListener menuItemLoadFromDirListener) {
    menuItemLoadFromDir.addActionListener(menuItemLoadFromDirListener);
  }

  /**
   * Provides mechanism to add ActionListener to menu item about. Use this function to add the
   * controller's ActionListener for listening to events when user selects menu item about from the
   * file menu.
   *
   * @param menuItemAboutListener The Controller's ActionListener object to listen to events from
   *     the menu item about.
   */
  public void addMenuItemAboutListener(ActionListener menuItemAboutListener) {
    menuItemAbout.addActionListener(menuItemAboutListener);
  }

  /**
   * Provides mechanism to add ActionListener to menu item exit. Use this function to add the
   * controller's ActionListener for listening to events when user selects menu item exit from the
   * file menu.
   *
   * @param menuItemExitListener The Controller's ActionListener object to listen to events from the
   *     menu item exit.
   */
  public void addMenuItemExitListener(ActionListener menuItemExitListener) {
    menuItemExit.addActionListener(menuItemExitListener);
  }

  /**
   * Provides mechanism to add ActionListener to load data from file button. Use this function to
   * add the controller's ActionListener for listening to events when user presses load data from
   * file button.
   *
   * @param loadDataFromFileBtnListener The Controller's ActionListener object to listen to events
   *     from load data from file button.
   */
  public void addLoadDataFromFileBtnListener(ActionListener loadDataFromFileBtnListener) {
    loadDataFromFileBtn.addActionListener(loadDataFromFileBtnListener);
  }

  /**
   * Provides mechanism to add ActionListener to load data from directory button. Use this function
   * to add the controller's ActionListener for listening to events when user presses load data from
   * directory button.
   *
   * @param loadDataFromDirBtnListener The Controller's ActionListener object to listen to events
   *     from load data from directory button.
   */
  public void addLoadDataFromDirBtnListener(ActionListener loadDataFromDirBtnListener) {
    loadDataFromDirBtn.addActionListener(loadDataFromDirBtnListener);
  }

  /**
   * Provides mechanism to add ActionListener to load pattern button. Use this function to add the
   * controller's ActionListener for listening to events when user presses load pattern button.
   *
   * @param loadPatternBtnListener The Controller's ActionListener object to listen to events from
   *     load pattern button.
   */
  public void addLoadPatternBtnListener(ActionListener loadPatternBtnListener) {
    loadPatternBtn.addActionListener(loadPatternBtnListener);
  }

  /**
   * Provides mechanism to add ActionListener to search pattern button. Use this function to add the
   * controller's ActionListener for listening to events when user presses search pattern button.
   *
   * @param searchPatternBtnListener The Controller's ActionListener object to listen to events from
   *     search pattern button.
   */
  public void addSearchPatternBtnListener(ActionListener searchPatternBtnListener) {
    searchPatternBtn.addActionListener(searchPatternBtnListener);
  }

  // ... Setters to update the components.

  /**
   * Sets/updates load data from file label. This function sets/updates the name of the file to be
   * scanned in load data from file label with the string passed to it.
   *
   * @param fileName The file name to set/update in load data from file label.
   */
  public void setLoadDataFromFileLabel(String fileName) {
    loadDataFromFileLabel.setText(fileName);
  }

  /**
   * Sets/updates load data from directory label. This function sets/updates the name of the
   * directory to be scanned in load data from directory label with the string passed to it.
   *
   * @param dirName The directory name to set/update in load data from directory label.
   */
  public void setLoadDataFromDirLabel(String dirName) {
    loadDataFromDirLabel.setText(dirName);
  }

  /**
   * Sets/updates the load pattern label. This function sets/updates the name of the pattern file
   * containing the pattern/patterns to be searched in load pattern label with the string passed to
   * it.
   *
   * @param patternFileName The file name to set/update in load pattern label.
   */
  public void setLoadPatternLabel(String patternFileName) {
    loadPatternLabel.setText(patternFileName);
  }

  /**
   * Sets/updates the search results text area. This function sets/updates the text area with the
   * results passed to it.
   *
   * @param result The string containing results generated after searching for pattern/patterns.
   */
  public void setSearchResults(String result) {
    searchResults.setText(result);
  }

  // ... Getters to get UI components

  /**
   * Gets the loadDataFromDir JLabel.
   *
   * @return Returns a JLabel for the controller to manipulate.
   */
  public JLabel getLoadDataFromDirLabel() {
    return loadDataFromDirLabel;
  }

  /**
   * Gets the loadDataFromFileLabel JLabel.
   *
   * @return Returns a JLabel for the controller to manipulate.
   */
  public JLabel getLoadDataFromFileLabel() {
    return loadDataFromFileLabel;
  }
}
