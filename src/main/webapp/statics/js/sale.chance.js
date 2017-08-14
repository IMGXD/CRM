function formatState(value, row, index) {

//	JSON.parse(json字符串); eval
//	console.log(value + '----' + JSON.stringify(row) + '---' + index);
    if (value == null) {
        return '未知';
    } else if (value == 0) {
        return '未分配';
    } else if (value == 1) {
        return '已分配';
    }
}

function searchSaleChance() {
    var customerName = $("#s_customerName").val();
    var overview = $("#s_overview").val();
    var createMan = $("#s_createMan").val();
    var state = $("#s_state").combobox('getValue');
    console.info(state);
    $('#dg').datagrid('reload', {
        customerName: customerName,
        overview: overview,
        createMan: createMan,
        state: state
    });
}

// 弹出框弹出
function openSaleChanceAddDialog() {
    console.log("添加窗口");
    $("#dlg").dialog('open').dialog('setTitle', "添加营销机会");
}

//保存销售机会
function saveSaleChance() {
    var customerName = $("#customerId").combobox('getText');
    if (null == customerName || $.trim(customerName).length == 0) {
        alert("请选择客户");
        return;
    }
    $("#customerName").val(customerName);
    console.log($("#fm").serialize());
    $("#fm").form("submit", {
        url: 'add',
        onSubmit: function () {
            return $(this).form('validate');
        },
        success: function (response) {
            response = JSON.parse(response);
            if (response.status == 1) {
                $.messager.alert('提示', response.message);
                resetValue();
                $('#dlg').dialog('close');
                $('#dg').datagrid('reload');
            } else {
                alert(response.message);
            }
        }
    });
}
function closeSaleChanceDialog() {
    resetValue();
    $("#dlg").dialog('close');
}
function resetValue() {
    $("#fm").form("reset");
}

