package helper;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils {

    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    static NumberFormat nf = new DecimalFormat("R$ #,##0.00"
            , new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static String dateParaString(Date data) {
        return Utils.sdf.format(data);
    }

    public static Date stringParaDate(String data) {
        try {
            return Utils.sdf.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Double doubleParaString(Double valor) {
        try {
            return (Double)Utils.nf.parse(String.valueOf(valor));
        } catch (ParseException e) {
            return null;
        }
    }

    public static void pauser(int segundos) {
        try {
            TimeUnit.SECONDS.sleep(segundos);
        } catch (InterruptedException e) {
            System.out.println("Erro ao pauser por " + segundos + " segundos.");
        }
    }
}