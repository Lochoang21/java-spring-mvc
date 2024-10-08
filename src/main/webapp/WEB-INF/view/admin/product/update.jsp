<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
    <meta name="author" content="Hỏi Dân IT" />
    <title>Update User - Hỏi Dân IT</title>

    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script>
        $(document).ready(() =>{
            const avatarFile = $("avatarFile");
            const orgImage = "${newProduct.image}";
            if(orgImage){
                const urlImage = "/images/product/" + orgImage;
                $("#avatarPreview").attr("src", urlImage);
                $("#avatarPreview").css({ "display": "block" });
            }
            avatarFile.change(function (e) {
                const imgURL = URL.createObjectURL(e.target.files[0]);
                $("#avatarPreview").attr("src", imgURL);
                $("#avatarPreview").css({ "display": "block" });
            });
        });
    </script>
</head>

<body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp"/>
    <div id="layoutSidenav">
        <jsp:include page="../layout/sidebar.jsp"/>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Dashboard</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                        <li class="breadcrumb-item active">Update</li>
                    </ol>
                    <div class="mt-5">
                        <div class="row">
                            <div class="col-md-6 col-12 mx-auto"></div>
                            <h3>Update a Product</h3>
                            <hr />
                            <form:form method="post" action="/admin/product/create" modelAttribute="newProduct"
                                            class="row" enctype="multipart/form-data">
                                            <div class="mb-3 col-12 col-md-6">
                                                <c:set var="errorName">
                                                    <form:errors path="name" cssClass="invalid-feedback" />
                                                </c:set>
                                                <label for="exampleInputEmail1" class="form-label">Name: </label>
                                                <form:input type="text" class="form-control ${not empty errorName ? 'is-invalid' : ''}" id="exampleInputEmail1"
                                                    aria-describedby="emailHelp" path="name" />
                                                ${errorName}
                                            </div>
                                            <div class="mb-3 col-12 col-md-6">
                                                <c:set var="errorPrice">
                                                    <form:errors path="price" cssClass="invalid-feedback" />
                                                </c:set>
                                                <label for="exampleInputPassword1" class="form-label">Price:</label>
                                                <form:input type="number" class="form-control ${not empty errorPrice ? 'is-invalid' : ''}"
                                                    id="exampleInputPassword1" path="price" />
                                                    ${errorPrice}
                                            </div>
                                            <div class="mb-3 col-12 col-md-6">
                                                <c:set var="errorShortDes">
                                                    <form:errors path="shortDesc" cssClass="invalid-feedback" />
                                                </c:set>
                                                <label for="exampleInputEmail1" class="form-label">Short Description:</label>
                                                <form:input type="text" class="form-control ${not empty errorShortDes ? 'is-invalid' : ''}" id="exampleInputPhone1"
                                                    path="shortDesc" />
                                                    ${errorShortDes}
                                            </div>
                                            <div class="mb-3 col-12 col-md-6">
                                                <c:set var="errorQuantity">
                                                    <form:errors path="quantity" cssClass="invalid-feedback" />
                                                </c:set>
                                                <label for="exampleInputEmail1" class="form-label">Quantity:</label>
                                                <form:input type="text" class="form-control ${not empty errorQuantity ? 'is-invalid' : ''}" id="exampleInputFullName1"
                                                    path="quantity" />
                                                    ${errorQuantity}
                                            </div>
                                            <div class="mb-3">
                                                <c:set var="errorDescription">
                                                    <form:errors path="detailDesc" cssClass="invalid-feedback" />
                                                </c:set>
                                                <label for="exampleInputEmail1" class="form-label">Description:</label>
                                                <form:textarea class="form-control ${not empty errorDescription ? 'is-invalid' : ''}" id="exampleInputAddress1"
                                                    path="detailDesc" ></form:textarea>
                                                ${errorDescription}
                                            </div>
                                            <div class="mb-3 col-12 col-md-6">
                                                <label for="formFile" class="form-label">Factory: </label>
                                                <form:select class="form-select" path="factory">
                                                    <form:option value="APPLE">Apple (MacBook)</form:option>
                                                    <form:option value="ASUS">Asus</form:option>
                                                    <form:option value="LENOVO">Lenovo</form:option>
                                                    <form:option value="DELL">Dell</form:option>
                                                    <form:option value="ACER">Acer</form:option>
                                                    <form:option value="LG">LG</form:option>
                                                </form:select>
                                            </div>
                                            <div class="mb-3 col-12 col-md-6">
                                                <label for="formFile" class="form-label">Target: </label>
                                                <form:select class="form-select" path="target">
                                                    <form:option value="GAMEMING">Gaming</form:option>
                                                    <form:option value="SINHVIEN-VANPHONG">Sinh viên - Văn phòng</form:option>
                                                    <form:option value="THIET-KE-DO-HOA">Thiết kế đồ họa</form:option>
                                                    <form:option value="MONG-NHE">Mỏng nhẹ</form:option>
                                                    <form:option value="DOANH-NHAN">Doanh nhân</form:option>
                                                </form:select>
                                            </div>
                                            <div class="mb-3 col-12 col-md-6">
                                                <label for="avatarFile" class="form-label">Image: </label>
                                                <input class="form-control" type="file" id="avatarFile"
                                                    accept=".png, .jpg, .jpeg" name="hoidanitFile" />
                                            </div>
                                            
                                            <div class="col-12 mb-3">
                                                <img src="" id="avatarPreview" alt="avatar preview"
                                                    style="max-height: 250px; display: none;">
                                            </div>
                                            <!-- <div class="mb-3 form-check">
                      <input type="checkbox" class="form-check-input" id="exampleCheck1">
                      <label class="form-check-label" for="exampleCheck1">Check me out</label>
                    </div> -->
                                            <div class="col-12 mb-3"> <button type="submit"
                                                    class="btn btn-primary">Submit</button></div>

                                        </form:form>
                        </div>
                    </div>
                </div>
            </main>
            <jsp:include page="../layout/footer.jsp"/>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
</body>

</html>