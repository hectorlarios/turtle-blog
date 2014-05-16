var _instanceName = createRandomName(11);

var _calendarInstance;

function openCalendar(owner) 
{
    if (_calendarInstance && _calendarInstance.getOwner() == owner) 
    {
        _calendarInstance.hide();

        return;
    }

    var _currentDate = owner && owner.value!="" ? owner.value : getShortDateString();

    var _calendar = new Calendar(_currentDate);

    _calendar.setOwner(owner);

    _calendar.show();
}

function Calendar(currentDate) 
{
    var BORDER_COLOR = "#BBCCDD";
    var TABLE_OPEN_MAIN = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:11px;\">";
    var TABLE_OPEN = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
    var TABLE_CLOSE = "</table>";
    var TR_OPEN = "<tr>";
    var TR_CLOSE = "</tr>";
    var TR_BORDER_LINE = "<tr><td colspan=\"15\" style=\"height:1px;background-color:" + BORDER_COLOR + ";\"></td></tr>";
    var TD_OPEN = "<td>";
    var TD_CLOSE = "</td>";
    var TD_BORDER_LINE = "<td style=\"width:1px;background-color:" + BORDER_COLOR + ";\"></td>";
    var TD_CONTENT = "<td style=\"width:26px;height:20px;text-align:center;\">${VALUE}</td>";
    var TD_CONTENT_SELECTED = "<td style=\"width:26px;height:20px;text-align:center;background-color:#DDEEFF;\">${VALUE}</td>";
    var TD_TITLE = "<td style=\"width:136px;height:35px;text-align:center;color:#4b647d;font-weight:bold;font-size:12px;\">${VALUE}</td>";
    var ANCHOR = "<a href='javascript:void(\"\")' style='color:#0066FF;' onclick='_calendarInstance.setOwnerDate(\"${DATE}\")'>${VALUE}</a>";
    var SPAN_OTHER_MONTH = "<span style='color:#4b647d;'>${VALUE}</span>";
    var SPAN_SELECTED = "<span style='color:#FF3300;'>${VALUE}</span>";
    var WEEKDAY_LIST = new Array("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa");
    var DAYNAME_LIST = new Array("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
    var MONTH_NAME_LIST = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

    var _owner=null;

    var _currentDate = currentDate;

    var _originalDate = currentDate;

    var _originalDay = Number(_originalDate.split("/")[1]);

    var _day=0;

    var _month=0;

    var _year=0;

    var _dayList = new Array;

    var _div = document.getElementById(_instanceName);

    var _selectedIndex=-1;

    _calendarInstance = this;

    if (!_div) 
    {
        _div = document.createElement("div");

        _div.id = _instanceName;

        _div.style.backgroundColor = "#FFFFFF";

        _div.style.zIndex = "1";

        _div.style.position = "absolute";

        _div.style.width = "190px";
        
        document.body.appendChild(_div);
    }

    if (_currentDate != null) applyCurrentDate();

    this.getOwner = function () { return _owner; };

    this.setOwner = function (owner) 
    {
        _owner = owner;
        
        var _parent = owner;

        var _offsetTop = 0;

        var _offsetLeft = 0;

        while (_parent) 
        {
            if (!isNaN(_parent.offsetTop)) _offsetTop += _parent.offsetTop;

            if (!isNaN(_parent.offsetLeft)) _offsetLeft += _parent.offsetLeft;

            _parent = _parent.offsetParent;
        }
        
        _div.style.top = (_offsetTop+owner.offsetHeight) + "px";

        _div.style.left = (_offsetLeft-owner.clientLeft) + "px";
    };

    this.setOwnerDate = function (value) 
    {
        _owner.text = value;

        _owner.value = value;
        
        this.hide();
    };

    this.show = function () { draw(); };

    this.hide = function () { _div.innerHTML = ""; _owner = null; };

    this.setCurrentDate = function (currentDate) 
    {
        _currentDate = currentDate;

        applyCurrentDate();

        this.show();
    };

    function applyCurrentDate() 
    {
        var _datePart = _currentDate.split("/");

        _day = Number(_datePart[1]);

        _month = Number(_datePart[0])-1;

        _year = Number(_datePart[2]);

        var _lastMonthDayList = getLastMonthSharedWeekdays(_year, _month);

        var _nextMonthDayList = getNextMonthSharedWeekdays(_year, _month);

        var _monthDayList = new Array();

        var _monthTotalDays = getLastDayOfMonth(_year, _month);

        var i;

        var l = _monthTotalDays;

        _day = Math.min(_day, _monthTotalDays);

        for(i=0;i<l;i++) {
            
            var _currentDay = i + 1;

            var _anchor = ANCHOR.replace("${DATE}",(_month+1)+"/"+_currentDay+"/"+_year);

            var _value = _anchor.replace("${VALUE}", _currentDay);

            if (_currentDay == _day) 
            {
                if (_currentDate == _originalDate) _value = SPAN_SELECTED.replace("${VALUE}", _currentDay);

                _selectedIndex = i + _lastMonthDayList.length;
            }

            _monthDayList.push(_value);
        }

        _dayList = _lastMonthDayList.concat(_monthDayList, _nextMonthDayList);       
    }

    function draw() 
    {
        var _value = "";

        var i;

        var l = WEEKDAY_LIST.length;

        _value += TABLE_OPEN_MAIN + TR_CLOSE + TD_OPEN;

        _value += getMonthControl();

        _value += TD_CLOSE + TR_CLOSE;

        _value += TR_OPEN + TD_OPEN;

        _value += TABLE_OPEN + TR_BORDER_LINE;

        _value += TR_OPEN;

        _value += TD_BORDER_LINE;

        for (i = 0; i < l; i++) 
        {
            _value += TD_CONTENT.replace("${VALUE}", WEEKDAY_LIST[i]);

            _value += TD_BORDER_LINE;
        }

        _value += TR_CLOSE;

        l = _dayList.length;

        _value += TR_BORDER_LINE;

        var _col = 1;

        for (i = 0; i < l; i++) {

            if (_col == 1) _value += TR_OPEN + TD_BORDER_LINE;

            if (i == _selectedIndex) _value += TD_CONTENT_SELECTED.replace("${VALUE}", _dayList[i]);
            else _value += TD_CONTENT.replace("${VALUE}", _dayList[i]);

            _value += TD_BORDER_LINE;

            if (_col < 7) {
                _col++;
            }
            else {
                _col = 1;

                _value += TD_CLOSE;

                _value += TR_BORDER_LINE;                
            }
        }

        _value += TABLE_CLOSE;

        _value += TR_OPEN + TD_OPEN;

        _value += TABLE_OPEN + TR_OPEN;

        _value += TD_BORDER_LINE;

        _value += "<td style='width:188px;height:25px;text-align:center;vertical-align:middle;'>";

        _value += "<a href='javascript:void(\"\")' style='color:#0066FF;' onclick='_calendarInstance.hide()'>close</a>";

        _value += TD_CLOSE;

        _value += TD_BORDER_LINE;

        _value += TR_CLOSE;

        _value += TR_BORDER_LINE + TABLE_CLOSE;

        _value += TD_CLOSE + TR_CLOSE + TABLE_CLOSE;

        _div.innerHTML = _value;
    }

    function getMonthControl() 
    {
        var _weekday = getWeekday(_year, _month, _day);

        var _dayName = DAYNAME_LIST[_weekday];

        var _monthName = MONTH_NAME_LIST[_month];

        var _nextMonthDate = getNextMonth();

        var _lastMonthDate = getLastMonth();

        var _nextMonthValue = "<a href='javascript:void(\"\")' style='color:#0066FF;text-decoration:none;' onclick='_calendarInstance.setCurrentDate(\"${DATE}\")'>>></a>";

        var _lastMonthValue = "<a href='javascript:void(\"\")' style='color:#0066FF;text-decoration:none;' onclick='_calendarInstance.setCurrentDate(\"${DATE}\")'><<</a>";

        _nextMonthValue = _nextMonthValue.replace("${DATE}", (_nextMonthDate.getMonth() + 1) + "/" + _originalDay + "/" + _nextMonthDate.getFullYear());

        _lastMonthValue = _lastMonthValue.replace("${DATE}", (_lastMonthDate.getMonth() + 1) + "/" + _originalDay + "/" + _lastMonthDate.getFullYear());
        
        var _value = TABLE_OPEN + TR_BORDER_LINE;

        _value += TR_OPEN + TD_BORDER_LINE;

        _value += TD_CONTENT.replace("${VALUE}", _lastMonthValue);

        _value += TD_TITLE.replace("${VALUE}", _dayName+", "+_monthName + " " + _day + ", " + _year);

        _value += TD_CONTENT.replace("${VALUE}", _nextMonthValue);

        _value += TD_BORDER_LINE + TR_CLOSE + TABLE_CLOSE;

        return _value;
    }

    function getLastMonthSharedWeekdays(_year, _month) 
    {
        var _date = new Date(_year, _month, 1);

        var _weekday = _date.getDay();

        var _value = new Array;

        var i;

        var l = _weekday;

        _date.setDate(_date.getDate() - 1);

        var _currentDay = _date.getDate();

        for (i = 0; i < l; i++) 
        {
            var _currentValue = SPAN_OTHER_MONTH.replace("${VALUE}", _currentDay--);

            _value.push(_currentValue);
        }
        
        _value.reverse();

        return _value;
    }

    function getNextMonthSharedWeekdays(_year, _month) 
    {
        var _date = getNextMonth(_year, _month);

        _date.setDate(_date.getDate() - 1);

        var _weekday = _date.getDay();

        var _value = new Array;

        var i;

        var l = 6 - _weekday;

        var _currentDay = 1;

        for (i = 0; i < l; i++) 
        {
            var _currentValue = SPAN_OTHER_MONTH.replace("${VALUE}", _currentDay++);

            _value.push(_currentValue);
        }

        return _value;
    }

    function getNextMonth() 
    {
        var _nextMonth = _month < 11 ? _month + 1 : 0;

        var _nextYear = _month < 11 ? _year : _year + 1;

        var _value = new Date(_nextYear, _nextMonth, 1);

        return _value;
    }

    function getLastMonth() 
    {
        var _value = new Date(_year, _month, 1);

        _value.setDate(_value.getDate() - 1);

        return _value;
    }

    function getLastDayOfMonth(_year, _month) 
    {
        var _date = getNextMonth(_year, _month);

        _date.setDate(_date.getDate() - 1);

        var _value = _date.getDate();

        return _value;
    }

    function getWeekday(_year, _month, _day) 
    {
        var _date = new Date(_year, _month, _day);

        var _value = _date.getDay();

        return _value;
    }    
}
