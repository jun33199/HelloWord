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
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ���ش����</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author ����һ�飭�����
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
            com.ttsoft.common.util.Debug.out("�����걨���������ɹ�����IDeclaration codetable reloaded successfully.");
        }
        else
        {
            com.ttsoft.common.util.Debug.out("�����걨���������ʧ�ܣ���IDeclaration codetable reloading failed.");
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
        //String msg = "���������ɹ�";
        if (!CodeTableUtil.loadCodeTable(this.getServletContext()))
        {
            //msg = "���������ʧ��";
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