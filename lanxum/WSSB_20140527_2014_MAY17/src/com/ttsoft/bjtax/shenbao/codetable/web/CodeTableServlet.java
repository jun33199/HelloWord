package com.ttsoft.bjtax.shenbao.codetable.web;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.ttsoft.bjtax.shenbao.util.JspUtil;

/**
 *
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 加载代码表</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
 * @version 1.1
 */
public class CodeTableServlet extends HttpServlet
{
    public CodeTableServlet()
    {
    }

    public void init() throws ServletException
    {
        com.ttsoft.common.util.Debug.out("--internet declaration CodeTableServlet init");
        this.getServletContext().removeAttribute(CodeTableUtil.FLAG);

        if (CodeTableUtil.loadCodeTable(this.getServletContext()))
        {
            com.ttsoft.common.util.Debug.out("网上申报代码表载入成功－－IDeclaration codetable reloaded successfully.");
        }
        else
        {
            com.ttsoft.common.util.Debug.out("网上申报代码表载入失败－－IDeclaration codetable reloading failed.");
        }

        try
        {
            JspUtil.init(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doPost(request, response);
    }

    /**
     * doPost
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        com.ttsoft.common.util.Debug.out("--internet declaration CodeTableServlet init");
        this.getServletContext().removeAttribute(CodeTableUtil.FLAG);

        String path = request.getParameter("success");
        //String msg = "代码表载入成功";
        if (!CodeTableUtil.loadCodeTable(this.getServletContext()))
        {
            //msg = "代码表载入失败";
        }
        com.ttsoft.common.util.Debug.out("success path: " + path);

        try
        {
            JspUtil.init(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
//        response.sendRedirect(response.encodeRedirectURL(path + "?message=" + msg));
    }
}