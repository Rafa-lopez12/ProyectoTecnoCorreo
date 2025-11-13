// ChartGenerator.java
package utils;

import java.util.Map;

/**
 * @author Rafa
 */
public class ChartGenerator {
    
    private static final String[] COLORS = {
        "#FF6384", "#36A2EB", "#FFCE56", "#4BC0C0", "#9966FF",
        "#FF9F40", "#FF6384", "#C9CBCF", "#4BC0C0", "#FF6384"
    };
    
    public static String generatePieChart(Map<String, ? extends Number> data, String title, String chartId) {
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<script src='https://cdn.jsdelivr.net/npm/chart.js'></script>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
        html.append("h2 { color: #333; text-align: center; }");
        html.append(".chart-container { background: white; padding: 20px; border-radius: 8px; ");
        html.append("box-shadow: 0 2px 4px rgba(0,0,0,0.1); max-width: 800px; margin: 0 auto; }");
        html.append(".stats-table { width: 100%; margin-top: 30px; border-collapse: collapse; }");
        html.append(".stats-table th { background-color: #4CAF50; color: white; padding: 12px; text-align: left; }");
        html.append(".stats-table td { padding: 10px; border-bottom: 1px solid #ddd; }");
        html.append(".stats-table tr:hover { background-color: #f5f5f5; }");
        html.append("canvas { max-height: 400px; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        
        html.append("<h2>").append(title).append("</h2>");
        html.append("<div class='chart-container'>");
        html.append("<canvas id='").append(chartId).append("'></canvas>");
        html.append("</div>");
        
        // Tabla de datos
        html.append("<div class='chart-container'>");
        html.append("<table class='stats-table'>");
        html.append("<tr><th>Categoría</th><th>Valor</th></tr>");
        
        Number total = 0;
        for (Map.Entry<String, ? extends Number> entry : data.entrySet()) {
            html.append("<tr>");
            html.append("<td>").append(entry.getKey()).append("</td>");
            html.append("<td><strong>").append(formatNumber(entry.getValue())).append("</strong></td>");
            html.append("</tr>");
            total = total.doubleValue() + entry.getValue().doubleValue();
        }
        
        html.append("<tr style='background-color: #e8f5e9;'>");
        html.append("<td><strong>TOTAL</strong></td>");
        html.append("<td><strong>").append(formatNumber(total)).append("</strong></td>");
        html.append("</tr>");
        html.append("</table>");
        html.append("</div>");
        
        // Script de Chart.js
        html.append("<script>");
        html.append("const ctx = document.getElementById('").append(chartId).append("').getContext('2d');");
        html.append("const chart = new Chart(ctx, {");
        html.append("type: 'pie',");
        html.append("data: {");
        html.append("labels: [");
        
        boolean first = true;
        for (String key : data.keySet()) {
            if (!first) html.append(", ");
            html.append("'").append(escapeJavaScript(key)).append("'");
            first = false;
        }
        html.append("],");
        
        html.append("datasets: [{");
        html.append("data: [");
        first = true;
        for (Number value : data.values()) {
            if (!first) html.append(", ");
            html.append(value);
            first = false;
        }
        html.append("],");
        
        html.append("backgroundColor: [");
        for (int i = 0; i < data.size(); i++) {
            if (i > 0) html.append(", ");
            html.append("'").append(COLORS[i % COLORS.length]).append("'");
        }
        html.append("],");
        html.append("borderWidth: 2,");
        html.append("borderColor: '#fff'");
        html.append("}]");
        html.append("},");
        
        html.append("options: {");
        html.append("responsive: true,");
        html.append("maintainAspectRatio: true,");
        html.append("plugins: {");
        html.append("legend: { position: 'bottom', labels: { padding: 15, font: { size: 12 } } },");
        html.append("tooltip: {");
        html.append("callbacks: {");
        html.append("label: function(context) {");
        html.append("let label = context.label || '';");
        html.append("if (label) { label += ': '; }");
        html.append("label += context.parsed;");
        html.append("const total = context.dataset.data.reduce((a, b) => a + b, 0);");
        html.append("const percentage = ((context.parsed / total) * 100).toFixed(1);");
        html.append("label += ' (' + percentage + '%)';");
        html.append("return label;");
        html.append("}");
        html.append("}");
        html.append("}");
        html.append("}");
        html.append("}");
        html.append("});");
        html.append("</script>");
        
        html.append("</body>");
        html.append("</html>");
        
        return html.toString();
    }
    
    private static String formatNumber(Number value) {
        if (value instanceof Double || value instanceof Float) {
            return String.format("$%.2f", value.doubleValue());
        }
        return String.valueOf(value);
    }
    
    private static String escapeJavaScript(String str) {
        return str.replace("'", "\\'").replace("\"", "\\\"");
    }
}