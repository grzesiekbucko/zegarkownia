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



 if ($("#brandChart").length){
var data = {
                   labels: data_table,
                   datasets: [{
                       label: "marko",
                       data: [4, 8, 3, 5, 2, 3, 3, 3],
                       backgroundColor: 'rgba(153, 102, 255, 0.2)',
                       borderColor: 'rgba(153, 102, 255, 1)',
                       borderWidth: 1
                   },
                   {
                       label: "zearek.net",
                       data: [4, 8, 3, 5, 2, 3, 3, 3],
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

    var ctx = $('#brandChart');
    new Chart(ctx, {
    type: 'bar',
    options: options,
    data: data
 });

};




