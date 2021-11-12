
package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookiesServlet")
public class CookiesServlet extends HttpServlet{
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
     
            //suponemos que el usuario visita por primera vez nuestro sitio
            boolean nuevoUsuario = true;
            
            //Obtenemos el arreglo de cookies
            Cookie[] cookies = request.getCookies();
            
            //buscamos si ya existe una cookie creada con anterioridad 
            //llamada visitanteRecurrente, es decir, si ya navego por nuestro sitio web, ya deberia estar el cookie en su
            //navegador
        if(cookies != null){
            for(Cookie c : cookies){
                //si encuentra el nombre de visitanteRecurrente y el valor si quiere decir que ya habia visitado nuestra pagina
                if(c.getName().equals("visitanteRecurrente") && c.getValue().equals("si")){
                    //en ese caso el usuario no es nuevo y le asignamos el false y rompemos el ciclo.
                        nuevoUsuario = false;
                        break;
                }
            }
        }
        //luego verificamos si nuevo usuario es verdadero agregamos nuestra cookie para el registro
        // y le mandamos un mensaje de bienvenida, si es falso le mandamos un mensaje de saludo
        String mensaje = null;
        if(nuevoUsuario){
            Cookie visitanteCookie = new Cookie("visitanteRecurrente", "si");
            response.addCookie(visitanteCookie);
            mensaje = "Gracias por visitar nuestro sitio por primera vez";
        }
        else{
            mensaje = "Gracias por visitar nuevamente nuestro sitio";
        }
        //definimos que tipo de respuesta ofreceremos al navegador
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("Mensaje: " + mensaje);
        //cerramos el flujo
        out.close();
    }
}
