package com.plitsoft.ojt.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Interceptor Bean에서 지정된 형식으로 작성된 Interceptor handling method를 가져와 일괄적으로 처리합니다.
 *
 * <ul>
 *     <li>Class 명명규칙: 'InterceptControl' postfix가 필요합니다</li>
 *     <li>
 *          {@code InterceptClass.preInterceptor(
 *              HttpServletRequest request,
 *              HttpServletResponse response
 *          )}
 *          : controller전달 전 처리하는 interceptor를 실행합니다
 *     </li>
 *     <li>
 *          {@code InterceptClass.postInterceptor(
 *              HttpServletRequest request,
 *              HttpServletResponse response
 *          )}
 *          : controller에서 처리 후 처리 할 interceptor를 실행합니다
 *     </li>
 * </ul>
 *
 * @version 1.0
 * @author waffle
 */
@Component
public class InterceptController  implements HandlerInterceptor, ApplicationContextAware {

    public class InterceptorMethod {
        private Method method;
        private Object instance;

        public InterceptorMethod(Method method, Object instance) {
            this.method = method;
            this.instance = instance;
        }

        public void run(Object... args) {
            try {
                this.method.invoke(this.instance, args);
            } catch(IllegalAccessException | InvocationTargetException e) {}
        }
    }

    ApplicationContext context;

    private ApplicationContext getApplicationContext() {
        return this.context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler
    ) throws Exception {
        ApplicationContext context = this.getApplicationContext();
        for (InterceptorMethod handlerMethod : this.getInterceptHandlers(context)) {
            handlerMethod.run(request, response);
        }
        return true;
    }

    private List<InterceptorMethod> getInterceptHandlers(ApplicationContext context) {
        List<InterceptorMethod> preHandlers = this.getPreInterceptors(context);
        List<InterceptorMethod> postHandlers = this.getPostInterceptors(context);
        preHandlers.addAll(postHandlers);
        return preHandlers;
    }

    private List<InterceptorMethod> getPreInterceptors(ApplicationContext context) {
        return getInterceptorBeans(context)
                .map(v -> this.getInterceptorMethod(v, "pre"))
                .filter(Objects::nonNull)
                .toList();
    }

    private List<InterceptorMethod> getPostInterceptors(ApplicationContext context) {
        return getInterceptorBeans(context)
                .map(v -> this.getInterceptorMethod(v, "post"))
                .filter(Objects::nonNull)
                .toList();
    }

    private InterceptorMethod getInterceptorMethod(Object object, String prefix) {
        try {
            Method interceptorMethod = object.getClass().getMethod(prefix + "Interceptor");
            return new InterceptorMethod(interceptorMethod, object);
        } catch(NoSuchMethodException e) {
            return null;
        }
    }

    private Stream<Object> getInterceptorBeans(ApplicationContext context) {
        return Arrays.stream(context.getBeanDefinitionNames())
                .filter(beanName -> beanName.endsWith("InterceptControl"))
                .map(context::getBean);
    }
}
