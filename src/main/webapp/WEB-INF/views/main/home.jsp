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
                    <li class="active"><a href="#">Active</a></li>
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li class="dropdown-header">Dropdown header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-left">
                    <input type="text" class="form-control col-lg-8" placeholder="Search">
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Separated link</a></li>
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
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                                    Dropdown <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="#dropdown1" data-toggle="tab">Action</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#dropdown2" data-toggle="tab">Another action</a></li>
                                </ul>
                            </li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade active in" id="journal">
                                <button onclick="getJournal()" id="callJournal" class="btn btn-primary">Get Journal</button>
                                <br/><br/>
                                <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica. Reprehenderit butcher retro keffiyeh dreamcatcher synth. Cosby sweater eu banh mi, qui irure terry richardson ex squid. Aliquip placeat salvia cillum iphone. Seitan aliquip quis cardigan american apparel, butcher voluptate nisi qui.</p>
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
                            <div class="tab-pane fade" id="dropdown1">
                                <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify squid 8-bit cred pitchfork.</p>
                            </div>
                            <div class="tab-pane fade" id="dropdown2">
                                <p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they sold out farm-to-table VHS viral locavore cosby sweater.</p>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
        </div>

    </body>
</html>
