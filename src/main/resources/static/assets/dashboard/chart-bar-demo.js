// Set new default font family and font color to mimic Bootstrap's default styling
/*Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';*/

// Bar Chart Example
var ctx = document.getElementById("myBarChart");
var myLineChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: labelsBarChart,
        datasets: [{
            label: "Consommation",
            backgroundColor: "#198754",
            borderColor: "#198754",
            data: datasConsommation,
        },
          {
            label: "Production",
            backgroundColor: "rgb(216,2,52)",
            borderColor: "rgb(216,2,52)",
            data: datasProduction,
        }],
    },
    options: {
        legend: {
            display: false
        },
        responsive: true,
        scales: {
            x: {
                stacked: false,
            },
            y: {
                stacked: false
            }
        }
    }
});


var ctx1 = document.getElementById("myBarChart1");
var myLineChart1 = new Chart(ctx1, {
    type: 'bar',
    data: {
        labels: labelsBarChart1,
        datasets: [{
            label: "Nombre de marques",
            backgroundColor: "#ffc107",
            borderColor: "#ffc107",
            data: datasBarChart1,
        }]
    },
    options: {
        indexAxis: 'y',
        legend: {
            display: false
        },
        responsive: true,
        scales: {
            x: {
                stacked: false,
            },
            y: {
                stacked: false
            }
        }
    }
});


var ctx2 = document.getElementById("myBarChart2");
var myLineChart2 = new Chart(ctx2, {
    type: 'bar',
    data: {
        labels: labelsBarChart2,
        datasets: [{
            label: "Nombre de versions",
            backgroundColor: "#0d6efd",
            borderColor: "#0d6efd",
            data: datasBarChart2,
        }]
    },
    options: {
        legend: {
            display: false
        },
        responsive: true,
        scales: {
            x: {
                stacked: false,
            },
            y: {
                stacked: false
            }
        }
    }
});
