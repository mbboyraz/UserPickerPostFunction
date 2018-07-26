$('select').change(function () {

    var value = $(this).val();

    $(this).siblings('select').children('option').each(function () {
        if ($(this).val() === value) {
            $(this).attr('disabled', true).siblings().removeAttr('disabled');
        }
    });

});