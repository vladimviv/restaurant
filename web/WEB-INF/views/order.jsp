<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заказы</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <%--<script src="js/jquery.min.js" type="text/javascript"></script>--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<script type="text/javascript">
    var service = '/order';
    var RestGet = function (id) {
        $.ajax({
            type: 'GET',
            url: service + "/get/" + id,
            dataType: 'json',
            async: false,
            success: function (result) {
                order(result);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    function order(result) {
        var headTxt = "Номер заказа: " + result.id + "<br>" +
            "Время приема заказа: " + result.date + "<br>" +
            "Время выполнения заказа: " + result.date_ready + "<br>" +
            "Заказчик: " + result.customer + "<br>" +
            "Номер столика: " + result.tableNumber + "<br>" +
            "Статус заказа: " + result.status + "<br>" +
            "Дополнительная информация: " + result.comment + "<br>";
        var i;
        var itemTxt = "";
        for (i = 0; i < result.items.length; i++) {
            for (x in result.items[i]) {
                itemTxt += (result.items[i][x] + " ");
            }
            itemTxt += "<br>";
        }
        $('#response').html(headTxt + itemTxt);
    };

    var RestPut = function (client_name, client_description) {
        var JSONObject = {
            'name': client_name,
            'description': client_description
        };
        $.ajax({
            type: 'PUT',
            url: service + "/add",
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };
    var RestPost = function () {
        var JSONObject = {
            'id': $('#putId').val(),
            'name': $('#putName').val(),
            'description': $('#putDescription').val()
        };
        $.ajax({
            type: 'PUT',
            url: service + "/update",
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };
    var RestGetAll = function () {
        $.ajax({
            type: 'GET',
            url: service + '/all',
            datatype: 'json',
            async: false,
            success: function (result) {
                orderAll(result)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    function orderAll(result) {
        var j;
        var i;
        var itemTxt = "";
        var headTxt = "";
        var txt="";
        for (j = 0; j < result.length; j++) {
             headTxt="";
             headTxt = "Номер заказа: " + result[j].id + "<br>" +
                "Время приема заказа: " + result[j].date + "<br>" +
                "Время выполнения заказа: " + result[j].date_ready + "<br>" +
                "Заказчик: " + result[j].customer + "<br>" +
                "Номер столика: " + result[j].tableNumber + "<br>" +
                "Статус заказа: " + result[j].status + "<br>" +
                "Дополнительная информация: " + result[j].comment + "<br>";
             itemTxt = "";
             for (i = 0; i < result[j].items.length; i++) {
                for (x in result[j].items[i]) {
                    itemTxt += (result[j].items[i][x] + " ");
                }
                itemTxt += "<br>";
             }
             txt += headTxt + itemTxt + "<hr>";
        }

        $('#response').html(txt);
    };

    var RestDelete = function (id) {
        $.ajax({
            type: 'DELETE',
            url: service + "/delete/" + id,
            datatype: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

</script>

<div class="panel panel-default">
    <div class="panel-heading">
        Работа с заказами
    </div>

    <div class="panel-body">
        <table class="table">
            <tbody>
            <tr>
                <td>Все заказы</td>
                <td>
                    <button type="button" onclick="RestGetAll()">Показать</button>
                </td>
            </tr>
            <tr>
                <td>Заказ с номером:</td>
                <td><input id="getDocumentId" value=""></td>
                <td>
                    <button type="button" onclick="RestGet($('#getDocumentId').val())">Показать</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <strong>RESPONSE</strong>
        </div>
        <div class="panel-body" id="response"></div>
    </div>
</div>
</body>
</html>
