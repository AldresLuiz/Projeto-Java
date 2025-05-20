package dev.aldres.gameLogic;

import java.awt.Graphics;
import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import dev.aldres.annotations.PlayerSetter;
import dev.aldres.annotations.RenderPlayerGraphics;
import dev.aldres.annotations.RenderProperties;
import dev.aldres.annotations.RenderUIGraphics;
import dev.aldres.annotations.RenderWorldGraphics;
import dev.aldres.annotations.UpdateGameTicks;
import dev.aldres.annotations.WorldSetter;
import dev.aldres.entitys.Player;
import dev.aldres.gameCore.gameCanvas;

public class RenderAndUpdate {

    gameCanvas gc;
    Graphics g;
    Point PlayerPos;

    public RenderAndUpdate(gameCanvas gp, Graphics graphics) {
        gc = gp;
        g = graphics;
        discoverAndCache();
    }

    private static class RenderCall {
        Object obj;
        Method method;
        RenderCall(Object obj, Method method) {
            this.obj = obj;
            this.method = method;
        }
    }

    private List<RenderCall> RenderGraphics = new ArrayList<>();
    private List<RenderCall> UpdateTicks = new ArrayList<>();

    private void discoverAndCache(){
        RenderGraphics.clear();
        Reflections reflections = new Reflections("dev.aldres");

        Set<Class<?>> RenderWorldClasses = reflections.getTypesAnnotatedWith(WorldSetter.class);
        Set<Class<?>> RenderPlayerClasses = reflections.getTypesAnnotatedWith(PlayerSetter.class);
        Set<Class<?>> RenderClasses = reflections.getTypesAnnotatedWith(RenderProperties.class);

        for(Class<?> RenderPlayerClass : RenderPlayerClasses){
            try{
                Object obj = RenderPlayerClass.getDeclaredConstructor(gameCanvas.class).newInstance(gc);
                for(Method m : RenderPlayerClass.getDeclaredMethods()){
                    if(m.isAnnotationPresent(RenderPlayerGraphics.class)){
                        Field posField = RenderPlayerClass.getDeclaredField("Position");
                        posField.setAccessible(true);
                        PlayerPos = (Point) posField.get(obj);
                        RenderGraphics.add(new RenderCall(obj, m));
                        RenderGraphics.add(new RenderCall(obj, m));
                    }
                    if (m.isAnnotationPresent(UpdateGameTicks.class)) {
                        UpdateTicks.add(new RenderCall(obj, m));
                    }   
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(Class<?> RenderWorldClass : RenderWorldClasses){
            try{
                Object obj = RenderWorldClass.getDeclaredConstructor(gameCanvas.class,Point.class).newInstance(gc,PlayerPos);
                for(Method m : RenderWorldClass.getDeclaredMethods()){
                    if(m.isAnnotationPresent(RenderWorldGraphics.class)){
                        RenderGraphics.set(0,new RenderCall(obj, m));
                    }
                    if (m.isAnnotationPresent(UpdateGameTicks.class)) {
                        UpdateTicks.add(new RenderCall(obj, m));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(Class<?> RenderClass : RenderClasses){
            try {
                Object obj = RenderClass.getDeclaredConstructor(gameCanvas.class).newInstance(gc);
                for(Method m : RenderClass.getDeclaredMethods()){
                    if(m.isAnnotationPresent(RenderUIGraphics.class)){
                        RenderGraphics.add(new RenderCall(obj, m));
                    }
                    if (m.isAnnotationPresent(UpdateGameTicks.class)) {
                        UpdateTicks.add(new RenderCall(obj, m));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        Object player = null;
        Object world = null;

        for (RenderCall rc : RenderGraphics) {
            if(rc.obj.getClass().isAnnotationPresent(PlayerSetter.class)){
                player = rc.obj;
            }
            if(rc.obj.getClass().isAnnotationPresent(WorldSetter.class)){
                world = rc.obj;
            }
        }

        try {
            Field worldField = player.getClass().getDeclaredField("world");
            worldField.setAccessible(true);
            worldField.set(player, world);

            Field playerField = world.getClass().getDeclaredField("player");
            playerField.setAccessible(true);
            playerField.set(world, player);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void Render(){
        for(RenderCall renderCall : RenderGraphics){
            try {
                renderCall.method.invoke(renderCall.obj, g);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void UpdateTicks(){
        for(RenderCall obj : UpdateTicks){
            try {
                obj.method.invoke(obj.obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

