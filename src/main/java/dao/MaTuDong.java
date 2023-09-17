package dao;

public class MaTuDong {

	public String fomatAA000(String s) {
		String pos = s.substring(0, 2);
		int so = Integer.parseInt(s.substring(4));
		String stringSo = "000000";
		String ma1 = s.substring(2, 3);
		String ma2 = s.substring(3, 4);
		if (so >= 999) {
			if (ma2.equalsIgnoreCase("Z")) {
				if (!ma1.equalsIgnoreCase("Z")) {
					char a = (char) ((int) ma1.charAt(0) + 1);
					ma1 = String.valueOf(a);
					ma2 = "A";
				} else {
					System.err.println("Da toi gioi han ma");
					return null;
				}
			} else {
				// chua dat toi gioi han
				char a = (char) ((int) ma2.charAt(0) + 1);
				ma2 = String.valueOf(a);
			}
		} else {
			stringSo = String.format("%03d", so + 1);
		}
		return (pos + ma1 + ma2 + stringSo);
	}
}
