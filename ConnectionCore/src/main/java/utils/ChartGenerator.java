// ChartGenerator.java - Versión con QuickChart (gráficos como imágenes)
package utils;

import java.util.Map;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Rafa
 * Genera gráficos como IMÁGENES usando QuickChart.io
 * Los gráficos se muestran correctamente en correos electrónicos
 */
public class ChartGenerator {
    
    private static final String QUICKCHART_URL = "https://quickchart.io/chart";
    
    private static final String[] COLORS = {
        "rgb(255, 99, 132)",   // Rojo
        "rgb(54, 162, 235)",   // Azul
        "rgb(255, 205, 86)",   // Amarillo
        "rgb(75, 192, 192)",   // Verde azulado
        "rgb(153, 102, 255)",  // Morado
        "rgb(255, 159, 64)",   // Naranja
        "rgb(201, 203, 207)",  // Gris
        "rgb(233, 30, 99)",    // Rosa
        "rgb(103, 58, 183)",   // Morado oscuro
        "rgb(0, 188, 212)"     // Cyan
    };
    
    /**
     * Generar gráfico de pastel (pie chart)
     */
    public static String generatePieChart(Map<String, ? extends Number> data, String title, String chartId) {
        return generateChartHTML(data, title, "pie");
    }
    
    /**
     * Generar gráfico de dona (doughnut chart)
     */
    public static String generateDoughnutChart(Map<String, ? extends Number> data, String title, String chartId) {
        return generateChartHTML(data, title, "doughnut");
    }
    
    /**
     * Generar gráfico de barras (bar chart)
     */
    public static String generateBarChart(Map<String, ? extends Number> data, String title, String chartId) {
        return generateChartHTML(data, title, "bar");
    }
    
    /**
     * Método principal para generar HTML con gráficos
     */
    private static String generateChartHTML(Map<String, ? extends Number> data, String title, String chartType) {
        StringBuilder html = new StringBuilder();
        
        // Construir la URL del gráfico
        String chartUrl = buildChartUrl(data, title, chartType);
        
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<style>");
        html.append("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; ");
        html.append("padding: 20px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); margin: 0; }");
        html.append(".container { max-width: 900px; margin: 0 auto; }");
        html.append("h2 { color: white; text-align: center; font-size: 28px; ");
        html.append("text-shadow: 2px 2px 4px rgba(0,0,0,0.3); margin-bottom: 30px; }");
        html.append(".chart-container { background: white; padding: 30px; border-radius: 15px; ");
        html.append("box-shadow: 0 10px 40px rgba(0,0,0,0.2); margin-bottom: 30px; text-align: center; }");
        html.append(".chart-image { max-width: 100%; height: auto; border-radius: 10px; }");
        html.append(".stats-table { width: 100%; margin-top: 20px; border-collapse: collapse; ");
        html.append("background: white; border-radius: 10px; overflow: hidden; ");
        html.append("box-shadow: 0 5px 20px rgba(0,0,0,0.1); }");
        html.append(".stats-table th { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
        html.append("color: white; padding: 15px; text-align: left; font-weight: 600; font-size: 14px; }");
        html.append(".stats-table td { padding: 12px 15px; border-bottom: 1px solid #eee; font-size: 14px; }");
        html.append(".stats-table tr:hover { background-color: #f8f9fa; }");
        html.append(".stats-table tr:last-child td { border-bottom: none; }");
        html.append(".total-row { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
        html.append("color: white; font-weight: bold; }");
        html.append(".total-row td { border: none !important; }");
        html.append(".value-number { font-size: 1.1em; font-weight: 600; color: #667eea; }");
        html.append(".percentage { color: #666; font-size: 0.95em; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        
        html.append("<div class='container'>");
        html.append("<h2>").append(escapeHtml(title)).append("</h2>");
        
        // Imagen del gráfico
        html.append("<div class='chart-container'>");
        html.append("<img src='").append(chartUrl).append("' alt='").append(escapeHtml(title)).append("' class='chart-image' />");
        html.append("</div>");
        
        // Tabla de datos
        html.append("<div class='chart-container'>");
        html.append("<table class='stats-table'>");
        html.append("<thead><tr>");
        html.append("<th>Categoría</th>");
        html.append("<th style='text-align: right;'>Valor</th>");
        html.append("<th style='text-align: right;'>Porcentaje</th>");
        html.append("</tr></thead>");
        html.append("<tbody>");
        
        Number total = 0;
        for (Map.Entry<String, ? extends Number> entry : data.entrySet()) {
            total = total.doubleValue() + entry.getValue().doubleValue();
        }
        
        for (Map.Entry<String, ? extends Number> entry : data.entrySet()) {
            double percentage = (entry.getValue().doubleValue() / total.doubleValue()) * 100;
            html.append("<tr>");
            html.append("<td>").append(escapeHtml(entry.getKey())).append("</td>");
            html.append("<td class='value-number' style='text-align: right;'>").append(formatNumber(entry.getValue())).append("</td>");
            html.append("<td class='percentage' style='text-align: right;'>").append(String.format("%.1f%%", percentage)).append("</td>");
            html.append("</tr>");
        }
        
        html.append("</tbody>");
        html.append("<tfoot>");
        html.append("<tr class='total-row'>");
        html.append("<td><strong>TOTAL</strong></td>");
        html.append("<td style='text-align: right;'><strong>").append(formatNumber(total)).append("</strong></td>");
        html.append("<td style='text-align: right;'><strong>100%</strong></td>");
        html.append("</tr>");
        html.append("</tfoot>");
        html.append("</table>");
        html.append("</div>");
        
        html.append("</div>"); // Cierra container
        
        html.append("</body>");
        html.append("</html>");
        
        return html.toString();
    }
    
    /**
     * Construir URL de QuickChart con los datos
     */
    private static String buildChartUrl(Map<String, ? extends Number> data, String title, String chartType) {
        try {
            // Construir JSON de configuración del gráfico
            StringBuilder chartConfig = new StringBuilder();
            chartConfig.append("{");
            chartConfig.append("\"type\":\"").append(chartType).append("\",");
            chartConfig.append("\"data\":{");
            
            // Labels
            chartConfig.append("\"labels\":[");
            boolean first = true;
            for (String key : data.keySet()) {
                if (!first) chartConfig.append(",");
                chartConfig.append("\"").append(escapeJson(key)).append("\"");
                first = false;
            }
            chartConfig.append("],");
            
            // Datasets
            chartConfig.append("\"datasets\":[{");
            chartConfig.append("\"label\":\"").append(escapeJson(title)).append("\",");
            chartConfig.append("\"data\":[");
            first = true;
            for (Number value : data.values()) {
                if (!first) chartConfig.append(",");
                chartConfig.append(value);
                first = false;
            }
            chartConfig.append("],");
            
            // Colores
            chartConfig.append("\"backgroundColor\":[");
            for (int i = 0; i < data.size(); i++) {
                if (i > 0) chartConfig.append(",");
                chartConfig.append("\"").append(COLORS[i % COLORS.length]).append("\"");
            }
            chartConfig.append("],");
            
            chartConfig.append("\"borderWidth\":2,");
            chartConfig.append("\"borderColor\":\"white\"");
            chartConfig.append("}]");
            chartConfig.append("},");
            
            // Opciones
            chartConfig.append("\"options\":{");
            chartConfig.append("\"plugins\":{");
            chartConfig.append("\"legend\":{\"position\":\"bottom\"},");
            chartConfig.append("\"title\":{\"display\":true,\"text\":\"").append(escapeJson(title)).append("\",\"font\":{\"size\":18}}");
            chartConfig.append("}");
            chartConfig.append("}");
            chartConfig.append("}");
            
            // Encodear y construir URL
            String encodedConfig = URLEncoder.encode(chartConfig.toString(), StandardCharsets.UTF_8.toString());
            return QUICKCHART_URL + "?c=" + encodedConfig + "&width=600&height=400&backgroundColor=white";
            
        } catch (Exception e) {
            System.err.println("Error construyendo URL del gráfico: " + e.getMessage());
            return "";
        }
    }
    
    private static String formatNumber(Number value) {
        if (value instanceof Double || value instanceof Float) {
            return String.format("$%.2f", value.doubleValue());
        }
        return String.valueOf(value);
    }
    
    private static String escapeHtml(String str) {
        return str.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace(">", "&gt;")
                  .replace("\"", "&quot;")
                  .replace("'", "&#39;");
    }
    
    private static String escapeJson(String str) {
        return str.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\n", "\\n")
                  .replace("\r", "\\r")
                  .replace("\t", "\\t");
    }
}