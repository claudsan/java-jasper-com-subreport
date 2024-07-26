package br.com.claudsan.jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws JRException {


        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        var relatorio = classloader.getResourceAsStream("exemplo.jrxml");
        var subRelatorio = classloader.getResourceAsStream("subrelatorio.jrxml");

        var subReport = JasperCompileManager.compileReport(subRelatorio);
        var jasperReport = JasperCompileManager.compileReport(relatorio);
        JRDataSource dataSource = new JRBeanCollectionDataSource(fakeData());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("subReportFile", subReport);

        var print =  JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(print,"relatorio.pdf");
    }

    private static List<Map<String, Object>> fakeData() {

        List<Map<String, Object>> itens1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("SUB_ITEM_ID",i);
            item.put("SUB_ITEM_NAME","DETALHE SOBRE O PRODUTO 1-"+i);
            itens1.add(item);
        }
        HashMap<String, Object> registro1 = new HashMap<>();
        registro1.put("ITEM_ID", 1);
        registro1.put("NOME", "PRODUTO 1");
        registro1.put("SUB_ITENS_LISTA", itens1);


        List<Map<String, Object>> itens2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> item21 = new HashMap<>();
            item21.put("SUB_ITEM_ID",i+10);
            item21.put("SUB_ITEM_NAME","DETALHE SOBRE O PRODUTO 2-"+i);
            itens2.add(item21);
        }
        Map<String, Object> registro2 = new HashMap<>();
        registro2.put("ITEM_ID", 2);
        registro2.put("NOME", "PRODUTO 2");
        registro2.put("SUB_ITENS_LISTA", itens2);

        List<Map<String, Object>> registros = new ArrayList<>();
        registros.add(registro1);
        registros.add(registro2);

        return registros;
    }
}