package org.moxieapps.gwt.highcharts.showcase.client.combination;

import com.smartgwt.client.widgets.Canvas;
import org.moxieapps.gwt.highcharts.client.*;
import org.moxieapps.gwt.highcharts.client.labels.DataLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.PiePlotOptions;
import org.moxieapps.gwt.highcharts.showcase.client.BaseChartExample;
import org.moxieapps.gwt.highcharts.showcase.client.ExampleFactory;

public class ColumnLinePieExample extends BaseChartExample {

    public ColumnLinePieExample(String title, String icon) {
        super(title, icon);
    }

    @Override
    public String getSourcePath() {
        return "combination/ColumnLinePieExample.java.html";
    }

    public static ExampleFactory getFactory() {
        return new ExampleFactory() {
            @Override
            public Canvas createChartCanvas() {
                return new ColumnLinePieExample(getTitle(), getIcon());
            }

            @Override
            public String getTitle() {
                return "Column, Line, and Pie";
            }

            @Override
            public String getIcon() {
                return "icons/16/combination_chart.png";
            }
        };
    }

    @Override
    public Chart createChart() {

        final Chart chart = new Chart()
            .setChartTitleText("Combination chart")
            .setToolTip(new ToolTip()
                .setFormatter(new ToolTipFormatter() {
                    public String format(ToolTipData toolTipData) {
                        String s;
                        if (toolTipData.getPointName() != null) { // the pie chart
                            s = toolTipData.getPointName() + ": " +
                                toolTipData.getYAsLong() + " fruits";
                        } else {
                            s = toolTipData.getXAsString() + ": " +
                                toolTipData.getYAsLong();
                        }
                        return s;
                    }
                })
            )
            .setLabelItems(
                new LabelItem()
                    .setHtml("Total fruit consumption")
                    .setStyle(new Style()
                        .setLeft("40px")
                        .setTop("8px")
                        .setColor("black")
                    )
            );

        chart.getXAxis()
            .setCategories("Apples", "Oranges", "Pears", "Bananas", "Plums");

        chart.addSeries(chart.createSeries()
            .setName("Jane")
            .setType(Series.Type.COLUMN)
            .setPoints(new Number[]{3, 2, 1, 3, 4})
        );
        chart.addSeries(chart.createSeries()
            .setName("John")
            .setType(Series.Type.COLUMN)
            .setPoints(new Number[]{2, 3, 5, 7, 6})
        );
        chart.addSeries(chart.createSeries()
            .setName("Joe")
            .setType(Series.Type.COLUMN)
            .setPoints(new Number[]{4, 3, 3, 9, 0})
        );
        chart.addSeries(chart.createSeries()
            .setName("Average")
            .setType(Series.Type.SPLINE)
            .setPoints(new Number[]{3, 2.67, 3, 6.33, 3.33})
        );
        chart.addSeries(chart.createSeries()
            .setName("Total consumption")
            .setType(Series.Type.PIE)
            .setPoints(new Point[]{
                // TODO: Add custom colors for each point
                new Point("Jane", 13),
                new Point("John", 23),
                new Point("Joe", 19)
            })
            .setPlotOptions(new PiePlotOptions()
                .setCenter(100, 80)
                .setSize(100)
                .setShowInLegend(false)
                .setDataLabels(new DataLabels()
                    .setEnabled(false)
                )
            )
        );

        return chart;
    }
}