import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JOptionPane;

public class PaymentController implements ActionListener {

	private PaymentView paymentView;
	@SuppressWarnings("unused")
	private DataAdapter dataAdapter;
	private BigDecimal localCopyOfAmountDue;
	private BigDecimal change = new BigDecimal("0.00");

	public PaymentController(PaymentView paymentView, DataAdapter dataAdapter) {
		this.paymentView = paymentView;
		this.dataAdapter = dataAdapter;

		paymentView.getBtnCash().addActionListener(this);
		paymentView.getBtnEBT().addActionListener(this);
		paymentView.getBtnCredit().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == paymentView.getBtnCash()) {
			String input = JOptionPane.showInputDialog("Enter tender Amount");
			try {
				// check to confirm is parsable.
				Double inputDouble = Double.parseDouble(input);
				if (inputDouble <= 0) {
					JOptionPane.showMessageDialog(null, "Cannot pay a non-positive amount!");
					return;
				}

				if (input.contains(".") && input.indexOf('.') < input.length() - 3) {
					// check to make sure has less than three places beyond
					// decimal place.
					int check = 1;
					try {
						check = Integer.parseInt(input.substring(input.indexOf(".") + 3, input.length()));
					} catch (Exception es) {

					}
					if (check != 0) {
						JOptionPane.showMessageDialog(null, "Cannot pay a fraction of a cent!");
						return;
					}
				}
				localCopyOfAmountDue = paymentView.getAmountDue().setScale(2, BigDecimal.ROUND_HALF_UP);
				// subtract amount.
				localCopyOfAmountDue = localCopyOfAmountDue
						.subtract(new BigDecimal(input).setScale(2, BigDecimal.ROUND_HALF_UP));
				paymentView.setAmountDue(localCopyOfAmountDue);
				paymentView.getAmountDueLabel().setText("Amount Due: $" + localCopyOfAmountDue);

				if (localCopyOfAmountDue.compareTo(change) <= 0) {
					change = localCopyOfAmountDue.multiply(new BigDecimal(-1.00));
					paymentView.getAmountDueLabel().setText("Change Due: $" + change);
					if (localCopyOfAmountDue.equals(change)) {
						JOptionPane.showMessageDialog(null, "Thank you for shopping with us!");
					} else {
						JOptionPane.showMessageDialog(null, "Your change is $" + change
								+ ". Please take your change from the machine. Thank you for shopping with us!");
					}
					Application.getInstance().getPaymentView().setVisible(false);

				}
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Invalid input!");
				return;
			}

		} else if (e.getSource() == paymentView.getBtnEBT()) {
			JOptionPane.showMessageDialog(null, "This function is not yet implemented!");
			return;
		} else if (e.getSource() == paymentView.getBtnCredit()) {
			JOptionPane.showMessageDialog(null, "This function is not yet implemented!");
			return;
		}
	}

}
