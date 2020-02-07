
$(document).ready(function(){

var select = new SlimSelect({
  select: '#zeg_net_select_brand',
  placeholder: 'Wybierz marki do porównania',
  selectByGroup: true,
  hideSelectedOption: true,
  closeOnSelect: false,
  searchText: 'Brak wyników',
})

$("#search_button").click( function()
           {
             alert(select.selected());
           }
      );



});
