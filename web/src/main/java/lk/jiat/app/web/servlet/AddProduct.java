package lk.jiat.app.web.servlet;

import jakarta.annotation.security.DeclareRoles;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.app.core.model.Product;
import lk.jiat.app.core.service.ProductService;

import java.io.IOException;

@ServletSecurity(@HttpConstraint(rolesAllowed = {"SUPER_ADMIN","ADMIN"}))
@WebServlet("/admin/add_product")
public class AddProduct extends HttpServlet {

    @EJB
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("product_name");
        String desc = request.getParameter("product_desc");
        String price = request.getParameter("product_price");
        String quantity = request.getParameter("product_quantity");
        String category = request.getParameter("product_category");

        Product product = new Product(name,desc,Double.parseDouble(price),Double.parseDouble(quantity),category);
        productService.addProduct(product);

        response.sendRedirect(request.getContextPath()+"/index.jsp");

    }
}
