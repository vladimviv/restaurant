<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заказы</title>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>--%>
    <%--<script src="js/jquery.min.js" type="text/javascript"></script>--%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
    #response {
        height:400px;
        overflow-y:auto;
        width:100%;
    }
    form {
        border-radius: 5px;
        background-color: #f2f2f2;
        padding: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
        display: none;
    }

</style>
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
                $('#response').html(order(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    var GetTable = function (id) {
        $.ajax({
            type: 'GET',
            url: service + "/table/" + id,
            dataType: 'json',
            async: false,
            success: function (result, textStatus) {
                $('#response').html(orderAll(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    function strDate(d) {
        var str = "";
        if (d != null) str = new Date(d).toUTCString();
        return str;
    }

    function orderItem(result) {
        var itemTxt = '<table class="table" border="1"><tr><th>Блюдо</th><th>Количество</th></tr>';
        for (i = 0; i < result.length; i++) {
            itemTxt += "<tr><td>" + result[i].name + "</td>";
            itemTxt += "<td>" + result[i].amount + "</td></tr>";
        }
        itemTxt = itemTxt + "</table>";
        return itemTxt;
    };

    function order(result) {
        var headTxt = "Заказ с номером: " + result.id + "<br>" +
            "Время приема заказа____: " + strDate(result.date) + "<br>" +
            "Время выполнения заказа: " + strDate(result.date_ready) + "<br>" +
            "Заказчик: " + result.customer + "<br>" +
            "Столик номер: " + result.tableNumber + "<br>" +
            "Статус заказа: " + result.status + "<br>" +
            "Дополнительная информация: " + result.comment + "<br>";
        if (result.items.length > 0) {
            headTxt = headTxt + orderItem(result.items);
        }
        return headTxt;
    };

    var RestAdd = function () {
        var JSONObject = {
            'customer': $('#customer').val(),
            'tableNumber': $('#tableNumber').val(),
            'comment': $('#comment').val(),
        };
        $.ajax({
            type: 'POST',
            url: service + "/add",
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: false,
            success: function (result, textStatus) {
                $('#response').html(order(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    var AddItem = function (id) {
        var JSONObject = {
            'name': $('#dish').val(),
            'amount': $('#amount').val(),
        };
        $.ajax({
            type: 'POST',
            url: service + "/additem/" + id,
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: false,
            success: function (result, textStatus) {
                $('#response').html(order(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
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
                $('#response').html(orderAll(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    function orderAll(result) {
        var j;
        var txt = "";
        for (j = 0; j < result.length; j++) {
            txt += order(result[j]) + "<hr>";
        }
        return txt;
    };

    var RestDelete = function (id) {
        var isDel = confirm("Вы действительно хотите удалить заказ " + id + " ?");
        if (isDel == false) return;
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
    function getForm(idForm) {
        var formShow = "#" + idForm;
        $("form").hide();
        $(formShow).show();
        $("#butDel").hide();
    }
    function getFormDel(idForm) {
        var formShow = "#" + idForm;
        $("#butDel").show();
        $("form").hide();
        $(formShow).show();
    }

    function doFunc(func) {
        $("form").hide();
        func();
    }

</script>

<div class="container-fluid">
    <ul class="nav nav-tabs">
        <li class="dropdown">
            <a href="#" data-toggle="dropdown" class="dropdown-toggle">Посмотреть Заказ(ы)<b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a href="#" onclick=doFunc(RestGetAll)>Все</a></li>
                <li><a href="#" onclick="getForm('formOrderId')">По номеру</a></li>
                <li class="divider"></li>
                <li><a href="#" onclick="getForm('formTable')">Для выбранного столика</a></li>
                <li><a href="#">Последний для выбранного столику</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" data-toggle="dropdown" class="dropdown-toggle">Добавить (Заказ/Блюдо)<b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a href="#" onclick="getForm('formOrderAdd')">Заказ</a></li>
                <li><a href="#" onclick="getForm('formItemAdd')">Блюдо</a></li>
            </ul>
        </li>
        <li class="active"><a href="#" onclick="getFormDel('formOrderId')">Удалить</a></li>
    </ul>
</div>

<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading">
            Работа с заказами
        </div>

        <div class="panel-body">
            <div>
                <form id="formOrderAll" class="form-inline">
                    <label for="butAll">Все заказы</label>
                    <button id="butAll" type="button" class="btn btn-default" onclick="RestGetAll()">Показать</button>
                </form>
            </div>
            <div>
                <form id="formOrderId" class="form-inline">
                    <label for="butId">Заказ с номером</label>
                    <input type="text" class="form-control" id="getDocumentId" value="">
                    <button id="butId" type="button" class="btn btn-default"
                            onclick="RestGet($('#getDocumentId').val())">Показать
                    </button>
                    <button id="butDel" type="button" class="btn btn-default"
                            onclick="RestDelete($('#getDocumentId').val())">Удалить
                    </button>
                </form>
            </div>
            <div>
                <form id="formTable" class="form-inline">
                    <label for="getTable">Столик с номером</label>
                    <input type="number" class="form-control" id="getTable" value="">
                    <button id="butTable" type="button" class="btn btn-default"
                            onclick="GetTable($('#getTable').val())">Показать
                    </button>
                </form>
            </div>
            <form id="formOrderAdd" class="form-inline">
                <div class="form-group">
                    <label for="customer">Заказчик</label>
                    <input type="text" class="form-control" id="customer">
                </div>
                <div class="form-group">
                    <label for="tableNumber">Столик №</label>
                    <input type="number" class="form-control" id="tableNumber">
                </div>
                <div class="form-group">
                    <label for="comment">Доп. информация</label>
                    <input type="text" class="form-control" id="comment" size="80">
                </div>
                <button type="button" class="btn btn-default" onclick="RestAdd()">Новый заказ</button>
            </form>
            <form id="formItemAdd" class="form-inline">
                <div class="form-group">
                    <label for="order">Заказ №</label>
                    <input type="number" class="form-control" id="order">
                </div>
                <div class="form-group">
                    <label for="dish">Блюдо</label>
                    <input type="text" class="form-control" id="dish">
                </div>
                <div class="form-group">
                    <label for="comment">Количество</label>
                    <input type="number" class="form-control" id="amount">
                </div>
                <button type="button" class="btn btn-default" onclick="AddItem($('#order').val())">Добавить блюдо
                </button>
            </form>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <strong>Результат запроса</strong>
            </div>
            <div class="panel-body" id="response" ></div>

        </div>
    </div>
</div>
</body>
</html>
