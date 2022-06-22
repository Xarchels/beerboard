// Pie Chart Example
var ctx = document.getElementById("myPieChart");

var colors = [];

for (var i=0; i<labelsPieChart.length; i++){
  var randomColor = Math.floor(Math.random()*16777215).toString(16);
  colors[i] = "#"+randomColor;
}

var myPieChart = new Chart(ctx, {
  type: 'pie',
  data: {
    labels: labelsPieChart,
    datasets: [{
      data: datasPieChart,
      backgroundColor: colors,
    }],
  },
});
