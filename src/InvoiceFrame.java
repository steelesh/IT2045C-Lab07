import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InvoiceFrame extends JFrame {
    JPanel main;
    JPanel titlePnl;
    JLabel title;
    JPanel invoicePnl;
    JTextArea billingInfo;
    JTextField businessName;
    JTextField street;
    JTextField city;
    JTextField zip;
    JTextField state;
    JTextArea itemInfo;
    JScrollPane scroller;
    JTextField itemDesc;
    JTextField quantity;
    JTextField price;
    double runningTotal;
    JTextArea amtDue;
    JPanel ctrlPnl;
    JButton quitBtn;
    JButton addItem;
    public InvoiceFrame(){
        main = new JPanel();
        main.setLayout(new BorderLayout());
        setSize(720, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        createTitlePnl();
        main.add(titlePnl, BorderLayout.NORTH);
        createInvoicePnl();
        main.add(invoicePnl, BorderLayout.CENTER);
        createCtrlPnl();
        main.add(ctrlPnl, BorderLayout.SOUTH);
        add(main);
        setVisible(true);
    }
    private void createTitlePnl(){
        titlePnl = new JPanel();
        titlePnl.setBackground(Color.WHITE);
        title = new JLabel("INVOICE");
        title.setFont(new Font("Dialog", Font.BOLD, 40));
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.NORTH);
        titlePnl.add(title);
    }
    private void createInvoicePnl() {
        invoicePnl = new JPanel();
        invoicePnl.setBackground(Color.WHITE);
        billingInfo = new JTextArea(6, 36);
        billingInfo.setFont(new Font("Dialog", Font.PLAIN, 20));
        billingInfo.setBackground(Color.WHITE);
        billingInfo.setEditable(false);
        billingInfo.append("\n");
        businessName = new JTextField();
        street = new JTextField();
        city = new JTextField();
        state = new JTextField();
        zip = new JTextField();
        Object[] billingAddress = {
          "Business Name: ", businessName,
          "Street: ", street,
          "City: ", city,
          "State: ", state,
          "Zip: ", zip,
        };
        int confirm = JOptionPane.showConfirmDialog(null, billingAddress,"Billing Info", JOptionPane.OK_CANCEL_OPTION);
        if (confirm == JOptionPane.OK_OPTION){
            String inputName = businessName.getText();
            String inputStreet = street.getText();
            String inputCity = city.getText();
            String inputState = state.getText();
            String inputZip = zip.getText();
            billingInfo.append("  " + inputName + "\n  " + inputStreet + "\n  " + inputCity + ", " + inputState + " " + inputZip + "\n\n");
            billingInfo.append("=============================================\n");
            billingInfo.append("   Item\t                         Qty\tPrice\tTotal\n");
        }
        itemInfo = new JTextArea(15, 37);
        scroller = new JScrollPane(itemInfo);
        scroller.setBorder(BorderFactory.createEmptyBorder());
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        itemInfo.setFont(new Font("Dialog", Font.PLAIN, 20));
        itemInfo.setBackground(Color.WHITE);
        itemInfo.setEditable(false);
        amtDue = new JTextArea(2, 37);
        amtDue.setFont(new Font("Dialog", Font.BOLD, 20));
        amtDue.setBackground(Color.WHITE);
        amtDue.setEditable(false);
        amtDue.setText("=============================================\n  AMOUNT DUE: $" + String.format("%.2f", runningTotal));
        invoicePnl.add(billingInfo);
        invoicePnl.add(scroller);
        invoicePnl.add(amtDue);
    }
    private void createCtrlPnl(){
        ctrlPnl = new JPanel();
        ctrlPnl.setLayout(new GridLayout(1, 2));
        ctrlPnl.setBackground(Color.WHITE);
        addItem = new JButton("Add Items");
        addItem.setFont(new Font("Dialog", Font.PLAIN, 16));
        addItem.addActionListener((ActionEvent ae) ->{
            itemDesc = new JTextField();
            quantity = new JTextField();
            price = new JTextField();
            state = new JTextField();
            zip = new JTextField();
            Object[] item = {
                    "Item Description: ", itemDesc,
                    "Quantity: ", quantity,
                    "Price: ", price,
            };
            int done = JOptionPane.showConfirmDialog(null, item,"Item Info", JOptionPane.OK_CANCEL_OPTION);
            if (done == JOptionPane.OK_OPTION){
                String inputItem = itemDesc.getText();
                int inputQuantity = Integer.parseInt(quantity.getText());
                double inputPrice = Double.parseDouble(price.getText());
                double totalPrice = inputPrice * inputQuantity;
                itemInfo.append(" "+ inputItem + "\t                        " + inputQuantity + "\t$" + String.format("%.2f", inputPrice) + "\t$" + String.format("%.2f", totalPrice) + "\n");
                runningTotal = runningTotal + totalPrice;
                amtDue.setText("=============================================\n  AMOUNT DUE: $" + String.format("%.2f", runningTotal));
            }
        });
        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Dialog", Font.PLAIN, 16));
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
        ctrlPnl.add(quitBtn);
        ctrlPnl.add(addItem);
    }
}