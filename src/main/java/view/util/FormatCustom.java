package view.util;

import java.text.DecimalFormat;
import java.util.Date;

public class FormatCustom {
	public static String chuyenDoiTienTe(double money) {
		DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###,###,### VND");
		return decimalFormat.format(money);
	}
	public static String chuyenDoiTienTeVer2(double money) {
		DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###,###,### đ");
		return decimalFormat.format(money);
	}

	public static double chuyenDoiNguocLaiTienTe(String money) {
		try {
			money = money.substring(0, money.length() - 4);
			String[] moneyArr = money.split(",");
			money = "";
			for (String string : moneyArr) {
				money += string;
			}
			return Double.parseDouble(money);
		} catch (Exception e) {
			System.err.println("chuyenDoiNguocLaiTienTe " + e.getMessage());
		}
		return -0;
	}

	@SuppressWarnings("deprecation")
	public static String dinhDanhThoiGian(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%02d", date.getHours()) + ":" + String.format("%02d", date.getMinutes()) + " - "
				+ String.format("%02d", date.getDate()) + "/" + String.format("%02d", date.getMonth() + 1) + "/"
				+ (date.getYear() + 1900);
	}

	public static String dinhDangGio(int tongPhut) {
		int gio = tongPhut / 60;
		int phut = tongPhut % 60;
		if (gio != 0) {
			return gio + " Giờ " + phut + " Phút ";
		}
		return phut + " Phút ";
	}
}
