$(document).ready(function(){

var select = new SlimSelect({
  select: '#zeg_net_select_brand',
  placeholder: 'Wybierz marki do porównania',
  selectByGroup: true,
  hideSelectedOption: true,
  closeOnSelect: false,
  searchText: 'Brak wyników',
});

$("#search_button").click( function (){
 $("#input_selectedBrand").val(select.selected());
           }
      );
});

 if ($("#magChart").length){
var data = {
                   labels: mag_label_table,
                   datasets: [{
                       label: "mag. marko",
                       data: mag_data_table_1,
                       backgroundColor: 'rgba(153, 102, 255, 0.2)',
                       borderColor: 'rgba(153, 102, 255, 1)',
                       borderWidth: 1
                   },
                   {
                       label: "mag. zewn.",
                       data: mag_data_table_2,
                       backgroundColor: 'rgba(75, 192, 192, 0.2)',
                       borderColor: 'rgba(75, 192, 192, 1)',
                       borderWidth: 1
                   }
                   ]
               };

var options =  {
                maintainAspectRatio: false,
                responsive: true,
scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
                  };

    var ctx = $('#magChart');
    new Chart(ctx, {
    type: 'bar',
    options: options,
    data: data
 });

};

 if ($("#brandChart").length){
var data = {
                   labels: brand_label_table,
                   datasets: [{
                       label: "marko.pl",
                       data: brand_data_table_1,
                       backgroundColor: 'rgba(54, 162, 235, 0.2)',
                       borderColor: 'rgba(54, 162, 235, 1)',

                       borderWidth: 1
                   },
                   {
                       label: "zegarek.net",
                       data: brand_data_table_2,
                       backgroundColor: 'rgba(255, 99, 132, 0.2)',
                       borderColor: 'rgba(255, 99, 132, 1)',
                       borderWidth: 1
                   }
                   ]
               };

var options =  {
                maintainAspectRatio: false,
                responsive: true,
scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
                  };

    var ctx = $('#brandChart');
    new Chart(ctx, {
    type: 'bar',
    options: options,
    data: data
 });

};




