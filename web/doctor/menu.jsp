<html>
<title>IITB Hospital - Menu</title>
<link rel="stylesheet" type="text/css" href="Aristo.css">
<link rel="stylesheet" type="text/css" href="style.css">
<!--link href='http://fonts.googleapis.com/css?family=Ubuntu:400,700' rel='stylesheet' type='text/css'-->

<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript">
	onload = function() {
					$(".login-screen").css("margin-top", ($(window).height()/2 - 200) + 'px');
					$(".menu-screen").css("margin-top", ($(window).height()/2 - 100) + 'px');
					
					//alert(document.documentElement.clientHeight + 'px');
				};
		
	onresize = function() {
					$(".login-screen", "#menu-screen").css("margin-top", ($(window).height()/2 - 200) + 'px');
					$(".menu-screen").css("margin-top", ($(window).height()/2 - 100) + 'px');
					
					//alert(document.documentElement.clientHeight + 'px');
				};
				
	$(function(){			
		$("#app_but").button();
		$("#pres_but").button();
		$("#med_but").button();
		$("#logout-button").button({
                    icons: {
                        secondary: 'ui-icon-transferthick-e-w'
                    }
                });
	});

</script>

<body>
	<div class="menu-screen" style="height: 200px; width: 410px; position: relative;">
			<div class="logout-button-div" style="right: 11px; position: absolute;"><a id="logout-button" href="../doctorLogout">Logout</a></div>
            <!--a href="../doctorAppointments" id="app_but" style="margin-top: 64px; margin-left: 27px;"><div class="big-button">MY</br><img src="images/app.png"></br>APPOINTMENTS</div></a-->
            <a href="../doctorGenerateNewPrescription"  id="pres_but" style="margin-top: 64px; margin-left: 27px;"><div class="big-button">WRITE A</br><img src="images/write_pres.png" height="35"></br>PRESCRIPTION</div></a>
            <a href="doctor_med_leave.jsp"  id="med_but" style="margin-top: 64px; margin-left: 27px;"><div class="big-button">WRITE A</br><img src="images/med.png"></br>MEDICAL LEAVE</div></a>
	</div>
</body>
</html>
