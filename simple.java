package driverProj;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.io.FileNotFoundException;
import java.io.FileWriter;
public class simple {  
    static LinkedList drivers;

    public simple(LinkedList driversA){ 
        drivers = driversA;
        JFrame frame =new JFrame("System");//creating instance of JFrame
        JTable table = new JTable(drivers.size(), 16){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column != 15 || row != 0? true : false;
                }
            };
        String[] columnNames = ((LinkedList<String>)(drivers.get(0))).toArray(new String[15]);
        JTextField[] search = new JTextField[14];
        String s1[] = {  "All", "Restricted", "Safe"}; 
        JComboBox c1 = new JComboBox(s1); 
        table.setSelectionMode(0);

        //table.setColumnModel();
        for(int i = 0; i < drivers.size(); i++){
            for (int j = 0; j < 15; j++){
                String val = ((LinkedList<String>)(drivers.get(i))).get(j);
                if (i == 0){
                    table.getColumnModel().getColumn(j).setHeaderValue(val);
                    table.getTableHeader().repaint();

                }
                if (val.equals("0") && j == 14){
                    table.setValueAt("no", i , j); 
                    ((LinkedList<String>)(drivers.get(i))).set(j, "no");
                }else if(val.equals("1") && j == 14){
                    table.setValueAt("yes", i , j); 
                    ((LinkedList<String>)(drivers.get(i))).set(j, "yes");
                }else
                    table.setValueAt(val, i, j);
            }
            table.setValueAt("viewDriver", i, 15);

        } 

        for (int i = 1; i < 14; i++){
            search[i] = new JTextField();
            if (i < 12)
                search[i].setBounds(i*118, 0, 118, 20);
            else
                search[i].setBounds(i*118+118, 0, 118, 20);
            frame.add(search[i]);

        }
        c1.setBounds(118*12, 0, 118, 20);
        frame.add(c1);

        frame.setSize(1920, 1080);
        frame.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,20, 1900, 1080);

        frame.add(scrollPane);
        frame.setVisible(true);
        
        
        java.util.List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
        for (int i = 0; i < 15; i++)
            filters.add(RowFilter.regexFilter(""));
        table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {

                    if (e.getClickCount() == 1) {

                        System.out.println("here");
                        JTable target = (JTable)e.getSource();
                        int row = target.getSelectedRow();
                        int column = target.getSelectedColumn();
                        if (column > 14){
                            view(row, column);
                        }
                    }
                }
            });
        table.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        int row = table.getSelectedRow();
                        int column = table.getSelectedColumn();

                        String resul = table.getValueAt(row, column).toString();
                        ((LinkedList<String>)(drivers.get(row))).set(column, resul);
                        save();

                    }
                }
            });

        search[1].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                        filters.set(1, RowFilter.regexFilter(search[1].getText(), 1));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);

                    }
                }

            });

        search[2].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                        filters.set(2, RowFilter.regexFilter(search[2].getText(), 2));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);

                    }
                }

            });
        search[3].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                        filters.set(3, RowFilter.regexFilter(search[3].getText(), 3));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);

                    }
                }

            });
        search[4].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                        filters.set(4, RowFilter.regexFilter(search[4].getText(), 4));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);

                    }
                }

            });

        search[5].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                        filters.set(5, RowFilter.regexFilter(search[5].getText(), 5));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);

                    }
                }

            });

        search[6].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                       filters.set(6, RowFilter.regexFilter(search[6].getText(), 6));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);

                    }
                }

            });

        search[7].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                        filters.set(7, RowFilter.regexFilter(search[7].getText(), 7));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);

                    }
                }

            });

        search[8].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                        filters.set(8, RowFilter.regexFilter(search[8].getText(), 8));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);

                    }
                }

            });

        search[9].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                       filters.set(9, RowFilter.regexFilter(search[13].getText(),9));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);


                    }
                }

            });

        search[10].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                       filters.set(10, RowFilter.regexFilter(search[10].getText(), 10));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);

                    }
                }

            });

        search[11].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                        filters.set(11, RowFilter.regexFilter(search[11].getText(),11));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);

                    }
                }

            });

        search[12].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                        filters.set(12, RowFilter.regexFilter(search[12].getText(), 13));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);

                    }
                }

            });

        search[13].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel()));
                        filters.set(13, RowFilter.regexFilter(search[13].getText(), 14));
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                        table.setRowSorter(sorter);

                    }
                }

            });
        c1.addItemListener (new ItemListener () {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    String item = (String)e.getItem();
                    System.out.println(item);
                    if (item.equals("Restricted")){
                        RowFilter<Object, Object> GreaterThan = new RowFilter<Object, Object>() {
                                public boolean include(Entry entry) {
                                    
                                    Integer val = 0;
                                    try{
                                        val = Integer.parseInt((String)entry.getValue(12));
                                    }
                                    catch(NumberFormatException e){
                                        val = 0;
                                    }
                                    System.out.println(val);
                                    return !(val.intValue()<16);
                                }
                            };
                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                        filters.set(14, GreaterThan);
                        sorter.setRowFilter(RowFilter.andFilter(filters)); 
                        table.setRowSorter(sorter);
                         
                    }
                    if(item.equals("Safe")){

                        RowFilter<Object, Object> LessThan = new RowFilter<Object, Object>() {
                                public boolean include(Entry entry) {
                                    Integer val = 0;
                                    try{
                                        val = Integer.parseInt((String)entry.getValue(12));
                                    }
                                    catch(NumberFormatException e){
                                        val = 0;
                                    }
                                    System.out.println(val);
                                    return val.intValue()<16;
                                }
                            };
                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                        filters.set(14, LessThan);
                        sorter.setRowFilter(RowFilter.andFilter(filters)); 
                        table.setRowSorter(sorter);
                    }
                    if (item.equals("All")){
                        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
                        filters.set(14, RowFilter.regexFilter("", 12));
                        sorter.setRowFilter(RowFilter.andFilter(filters)); 
                        table.setRowSorter(sorter);
                    }
                }
            });

    }

    public static void run(LinkedList drivers){
        simple cla = new simple(drivers);
    }

    public void save(){
        FileWriter csvWriter = null;
        try{
            csvWriter = new FileWriter("driverData2.csv");

            for (LinkedList<String> rowData : (LinkedList<LinkedList<String>>)drivers) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // File newFile = new File("C:/Users/arpit/coding/twiet/new.csv");
        // File oldFile = new File("C:/Users/arpit/coding/twiet/driverData2.csv");
        // newFile.renameTo(oldFile);
        // System.out.println("i ran");

    }

    public void view(int i, int j){
        JFrame frame = new JFrame("License");

        JLabel[] labels = new JLabel[15];
        JLabel picLabel = new JLabel("");
        JLabel picLabel2 = new JLabel("");

        JLabel[] ids = new JLabel[15];
        for (int k = 0; k < 15; k++){

            ids[k] = new JLabel(((LinkedList<String>)(drivers.get(0))).get(k) + ": ");
            labels[k] = new JLabel(((LinkedList<String>)(drivers.get(i))).get(k));
            labels[k].setFont(new Font("Serif", Font.PLAIN, 20));
            ids[k].setFont(new Font("Serif", Font.PLAIN, 20));
        }

        for (int k = 0; k < 15; k++){
            ids[k].setBounds(300,  k*30, 1000, 20);
            labels[k].setBounds(500, k*30, 1000, 20);
            frame.add(ids[k]);
            frame.add(labels[k]);
        }
        String id = (((LinkedList<String>)(drivers.get(i))).get(0));
        try{
            BufferedImage img = ImageIO.read(new File("C:/Users/arpit/coding/twiet/driverProj/Photos/p (" + id + ").png"));
            Image dimg = img.getScaledInstance(250,330,
                    Image.SCALE_SMOOTH);
            picLabel = new JLabel(new ImageIcon(dimg));
            if (Integer.parseInt(((LinkedList<String>)(drivers.get(i))).get(12)) >= 15){
                System.out.println("im here");
                BufferedImage img2 = ImageIO.read(new File("C:/Users/arpit/coding/twiet/driverProj/Photos/x.png"));
                Image dim2 = img2.getScaledInstance(250,330,
                        Image.SCALE_SMOOTH);
                picLabel2 = new JLabel(new ImageIcon(dim2));
            }
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        picLabel.setBounds(0, 0, 250,330);
        picLabel2.setBounds(0, 0, 250,330);

        frame.add(picLabel2);
        frame.add(picLabel);
        frame.setSize(800,500);//400 width and 500 height  
        frame.setLayout(null);
        frame.setVisible(true);
    }

}  