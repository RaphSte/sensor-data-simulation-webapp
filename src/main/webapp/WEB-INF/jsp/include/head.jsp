<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/css/sybit.css"/>
    <link rel="stylesheet" href="resources/css/bootstrap.min.4.1.3.css"/>
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap.bundle.min.4.1.3.js"></script>
    <script>
        var url = window.location.pathname;
        var filename = url.substring(url.lastIndexOf('/') + 1);
        filename = filename.replace("_", " ");
        window.document.title = filename + " | Machine-Simulator";
    </script>
    <%--
        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new�Date();
                a = s.createElement(o),
                    m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

            ga('set',�'anonymizeIp',�true
            )
            ;
            ga('create',�'�UA-133315072-1',�'auto'
            )
            ;
            ga('send',�'pageview'
            )
            ;
        </script>
        --%>

    <script src="<c:url value="/resources/js/scriptMsgs.js" />"></script>
    <%--
    <script type="text/javascript" src="https://cdns.gigya.com/JS/gigya.js?apikey=3_7_4Z2_pUSESQnB763uLda-d77h1Es9Bf6i-AhJO8QLoPGwOZzVLilUvm9mR5s9Jy"></script>
    <script type="text/javascript" src="https://cdns.gigya.com/js/gigyaGAIntegration.js"></script>
    --%>
</head>