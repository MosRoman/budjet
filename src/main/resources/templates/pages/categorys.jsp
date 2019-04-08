<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Video Content</title>
    <link href="/styles/css/style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<div id="wrapper">

    <div id="header">


        <p >Video Content<br><div align="center"  ><h1>Hello ${login}</h1></div></p>



    </div>
    <div id="content">



        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                    <ul id="groupList" class="nav navbar-nav">
                        <li><button type="button" id="menu" class="btn btn-default navbar-btn">Main menu</button></li>
                        <li><button type="button" id="add_category" class="btn btn-default navbar-btn">Add category</button></li>

                    </ul>





                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

        <table class="table table-striped">
            <thead>
            <tr>

                <td><b>Categorys of content</b></td>


            </tr>
            </thead>
            <c:forEach items="${categories}" var="categories">
                <tr>


                    <td>${categories.name}</td>
                    <td>

                        <c:url value="/delete_category" var="deleteCategory" />
                        <form action="${deleteCategory}" method="POST"  >
                            <button type="submit" id="delete" value="${categories.id}" name ="category" class="btn btn-default navbar-btn" >Delete</button>
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>

        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${allPages ne null}">
                    <c:forEach var="i" begin="1" end="${allPages}">
                        <li><a href="/categorys/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                    </c:forEach>
                </c:if>

            </ul>
        </nav>
    </div>

</div>
</div>





<script>


    $('#menu').click(function(){
        window.location.href='/';
    });
    $('#add_category').click(function(){
        window.location.href='/category_add_page';
    });


</script>
</body>
</html>
