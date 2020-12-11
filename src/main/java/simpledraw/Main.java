package simpledraw;

public class Main {
	/**Construct the application*/
	public Main() {
		MainFrame frame = new MainFrame();
		frame.validate();
		frame.setVisible(true);
		System.out.println(this.getClass().equals(super.getClass()));

	}

	/**Main method*/
	public static void main(String[] args) {
		new Main();
	}
}
