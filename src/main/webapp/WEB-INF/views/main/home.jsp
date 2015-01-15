<!DOCTYPE html>
<html>
    <head>
        <title>MyDiary</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" media="screen">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootswatch.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mydiary.css">
        <link href="${pageContext.request.contextPath}/resources/css/responsive-calendar.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootswatch.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/responsive-calendar.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/myDiary.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $(".responsive-calendar").responsiveCalendar({
                    time: '2015-01',
                    events: {
                        "2013-04-30": {"number": 5, "url": "http://w3widgets.com/responsive-slider"},
                        "2013-04-26": {"number": 1, "url": "http://w3widgets.com"},
                        "2013-05-03": {"number": 1},
                        "2013-06-12": {}}
                });
            });
        </script>
    </head>
    <body>

         <div class="navbar navbar-default">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
	
    <a class="navbar-brand" href="#">My Diary</a>
  </div>
  <div class="navbar-collapse collapse navbar-responsive-collapse">
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
    </ul>
    <form class="navbar-form navbar-left">
      <input type="text" class="form-control col-lg-8" placeholder="Search">
    </form>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#">Hello, User</a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Options <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="#">Logout</a></li>
          <li><a href="#">Profile</a></li>
          <li class="divider"></li>
          <li><a href="#">Settings</a></li>
        </ul>
      </li>
    </ul>
  </div>
</div>

        <div class="container">
            <div class="row">
                <div class="col-sm-3">

                    <!-- Responsive calendar - START -->
                    <div class="responsive-calendar">
                        <div class="controls">
                            <a class="pull-left" data-go="prev"><div class="btn btn-primary">Prev</div></a>
                            <h4><span data-head-year></span> <span data-head-month></span></h4>
                            <a class="pull-right" data-go="next"><div class="btn btn-primary">Next</div></a>
                        </div><hr/>
                        <div class="day-headers">
                            <div class="day header">Mon</div>
                            <div class="day header">Tue</div>
                            <div class="day header">Wed</div>
                            <div class="day header">Thu</div>
                            <div class="day header">Fri</div>
                            <div class="day header">Sat</div>
                            <div class="day header">Sun</div>
                        </div>
                        <div class="days" data-group="days">

                        </div>
                    </div>
                    <!-- Responsive calendar - END -->

                </div>
                <div class="col-sm-9">

                    <div class="jumbotron">


                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#journal" data-toggle="tab" aria-expanded="true">Journal</a></li>
                            <li class=""><a href="#talk" data-toggle="tab" aria-expanded="false">Talk</a></li>
                           
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade active in" id="journal">
                                <button onclick="getJournal()" id="callJournal" class="btn btn-primary">Get Journal</button>
                                <br/><br/>
                                <p id="journalContent">
                                    
                                </p>
                            </div>
                            <div class="tab-pane fade" id="talk">

                                <div class="question">
                                    <div class="form-group">
                                        <div class="row">
                                            
                                        <div class="col-sm-11">
                                        <input class="form-control" id="focusedInput" type="text" placeholder="Response">
                                        </div>
                                        <div class="col-sm-1">
                                        <button onclick="sendResponse()" id="sendResponse" class="btn btn-primary">Send</button>
                                        </div>
                                        </div>
                                </div>
                                <div class="Journal">
                                    <div class="form-group">
                                        <textarea class="form-control" id="textArea" value="This is focused..."></textarea>
                                    </div>
                                </div>

                            </div>
                            
                        </div>

                    </div>

                </div>
            </div>
        </div>

    </body>
</html>
