var grid, rows, cols, speed = 500;
var drawing = false;
var draw;

var $row = $("<div />", {
    class: 'row'
});
var $box = $("<div />", {
    class: 'box'
});

$(document).ready(function () {
    fillGrid();
});

$("#new").click(function () {
    if (!drawing) {
        rows = $("#height").val();
        cols = $("#width").val();
        grid = Array(rows * cols).fill(0);
        fillGrid();
    }
});

$("#next").click(function () {
    if (!drawing) {
        next();
    }
});

$("#speedUp").click(function () {

    speed -= 100;
    console.log(speed);

});

$("#speedDown").click(function () {
    speed += 100;
    console.log(speed);

});

$("#play").click(function () {
    if (drawing) {
        $(this).text("Play");
        clearInterval(draw);
        drawing = false;
    } else {

        console.log(speed)
        drawing = true;
        draw = setInterval(function () {
            next();
        }, speed);
        $(this).text("Stop");
    }
});


function fillGrid() {
    $("#grid").empty();
    for (var i = 0; i < rows; i++) {
        var row = $row.clone();
        for (var j = 0; j < cols; j++) {
            var cell = $box.clone();
            if (grid[i * rows + j] === 1) {
                cell.addClass('filled');
            }
            cell.attr('index', i * rows + j);

            cell.click(function () {
                if (!drawing) {
                    var value = grid[$(this).attr("index")];
                    if (value === 1) {
                        grid[$(this).attr("index")] = 0;
                        $(this).attr("class", "box");
                    } else {
                        grid[$(this).attr("index")] = 1;
                        $(this).attr("class", "box filled");
                    }
                }
            });

            row.append(cell);
        }
        $("#grid").append(row);
    }
}

function next() {
    var arr = {"content": grid, "width": cols, "height": rows};
    $.ajax({
        url: 'grid',
        type: 'POST',
        data: JSON.stringify(arr),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: true,
        success: function (data) {
            grid = data.content;
            fillGrid();
        }
    });

}