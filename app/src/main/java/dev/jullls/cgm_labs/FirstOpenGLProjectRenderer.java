package dev.jullls.cgm_labs;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;

public class FirstOpenGLProjectRenderer implements Renderer
{
    private float[] mModelMatrix = new float[16];
    private float[] mMVPMatrix = new float[16];

    /** Store our model data in a float buffer. */
    private final FloatBuffer mTriangle1Vertices1;
//    private final FloatBuffer mTriangle1Vertices2;
//    private final FloatBuffer mCircle1Vertices1;
//    private final FloatBuffer mCircle1Vertices2;
//    private final FloatBuffer mCircle1Vertices3;
//    private final FloatBuffer mCircle1Vertices4;
//    private final FloatBuffer mCircle1Vertices5;
//    private final FloatBuffer mCircle1Vertices6;
//    private final FloatBuffer mCircle1Vertices7;


    private int mColorHandle;

    /** How many bytes per float. */
    private final int mBytesPerFloat = 4;
    private int mPositionHandle;

    /** How many elements per vertex. */
    private final int mStrideBytes = 7 * mBytesPerFloat;

    /** Offset of the position data. */
    private final int mPositionOffset = 0;

    /** Size of the position data in elements. */
    private final int mPositionDataSize = 3;

    /** Offset of the color data. */
    private final int mColorOffset = 3;

    /** Size of the color data in elements. */
    private final int mColorDataSize = 4;
    private float x;
    public FirstOpenGLProjectRenderer() {
        // Define points for equilateral triangles.

        final float[] triangle1VerticesData1 = {
                // X, Y, Z,
                // R, G, B, A
                0.5f, -0.31f, 0.0f,
                1.0f, 0.0f, 0.0f, 1.0f,

                -0.5f, -0.31f, 0.0f,
                0.0f, 1.0f, 0.0f, 1.0f,

                0.5f, 0.31f, 0.0f,
                0.0f, 0.0f, 1.0f, 1.0f,

                -0.5f, 0.31f, 0.0f,
                1.0f, 0.0f, 0.0f, 1.0f

        };

        mTriangle1Vertices1 = ByteBuffer.allocateDirect(triangle1VerticesData1.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mTriangle1Vertices1.put(triangle1VerticesData1).position(0);

//        int cnt = 50 * 7;
//        float a;
//
//        final float[] circleVerticesData = new float[cnt];
//        circleVerticesData[0] = 0.0f;
//        circleVerticesData[1] = 0.0f;
//        circleVerticesData[2] = 0.0f;
//
//        circleVerticesData[3] = 1.0f;
//        circleVerticesData[4] = 0.231f;
//        circleVerticesData[5] = 0.514f;
//        circleVerticesData[6] = 1.0f;
//
//        for (int i = 7; i < cnt-7; i+=7){
//            a = (float) ((float)(i-7) * Math.PI * 2.0f / (float)(cnt-14));
//            circleVerticesData[i]= (float) (Math.cos(a)*0.5f);
//            circleVerticesData[i+1]= (float) (Math.sin(a)*0.31f);
//            circleVerticesData[i+2]=0.0f;
//            circleVerticesData[i+3] = 1.0f;
//            circleVerticesData[i+4] = 0.231f;
//            circleVerticesData[i+5] = 0.514f;
//            circleVerticesData[i+6] = 1.0f;
//        }
//
//        circleVerticesData[cnt-7] = circleVerticesData[7];
//        circleVerticesData[cnt-6] = circleVerticesData[8];
//        circleVerticesData[cnt-5] = circleVerticesData[9];
//
//        circleVerticesData[cnt-4] = 1.0f;
//        circleVerticesData[cnt-3] = 0.231f;
//        circleVerticesData[cnt-2] = 0.514f;
//        circleVerticesData[cnt-1] = 1.0f;
//
//        mCircle1Vertices1 = ByteBuffer.allocateDirect(circleVerticesData.length * mBytesPerFloat)
//                .order(ByteOrder.nativeOrder()).asFloatBuffer();
//        mCircle1Vertices1.put(circleVerticesData).position(0);
//
//        final float[] circleVerticesData1 = new float[cnt];
//        circleVerticesData1[0] = 0.3f * 0.5f;
//        circleVerticesData1[1] = 0.25f * 0.31f;
//        circleVerticesData1[2] = 0.0f;
//
//        circleVerticesData1[3] = 1.0f;
//        circleVerticesData1[4] = 1.0f;
//        circleVerticesData1[5] = 1.0f;
//        circleVerticesData1[6] = 1.0f;
//
//        for (int i = 7; i < cnt-7; i+=7){
//            a = (float) ((float)(i-7) * Math.PI * 2.0f / (float)(cnt-14));
//            circleVerticesData1[i]= (float) (Math.cos(a)*0.5f * 0.3f + circleVerticesData1[0]);
//            circleVerticesData1[i+1]= (float) (Math.sin(a)*0.31f * 0.3f + circleVerticesData1[1]);
//            circleVerticesData1[i+2]= 0.0f;
//            circleVerticesData1[i+3] = 1.0f;
//            circleVerticesData1[i+4] = 1.0f;
//            circleVerticesData1[i+5] = 1.0f;
//            circleVerticesData1[i+6] = 1.0f;
//        }
//
//        circleVerticesData1[cnt-7] = circleVerticesData1[7];
//        circleVerticesData1[cnt-6] = circleVerticesData1[8];
//        circleVerticesData1[cnt-5] = circleVerticesData1[9];
//
//        circleVerticesData1[cnt-4] = 1.0f;
//        circleVerticesData1[cnt-3] = 1.0f;
//        circleVerticesData1[cnt-2] = 1.0f;
//        circleVerticesData1[cnt-1] = 1.0f;
//
//        mCircle1Vertices2 = ByteBuffer.allocateDirect(circleVerticesData1.length * mBytesPerFloat)
//                .order(ByteOrder.nativeOrder()).asFloatBuffer();
//        mCircle1Vertices2.put(circleVerticesData1).position(1);
//
//        final float[] circleVerticesData2 = new float[cnt];
//        circleVerticesData2[0] = -0.3f * 0.5f;
//        circleVerticesData2[1] = 0.25f * 0.31f;
//        circleVerticesData2[2] = 0.0f;
//
//        circleVerticesData2[3] = 1.0f;
//        circleVerticesData2[4] = 1.0f;
//        circleVerticesData2[5] = 1.0f;
//        circleVerticesData2[6] = 1.0f;
//
//        for (int i = 7; i < cnt-7; i+=7){
//            a = (float) ((float)(i-7) * Math.PI * 2.0f / (float)(cnt-14));
//            circleVerticesData2[i]= (float) (Math.cos(a)*0.5f * -0.3f + circleVerticesData2[0]);
//            circleVerticesData2[i+1]= (float) (Math.sin(a)*0.31f * 0.3f + circleVerticesData2[1]);
//            circleVerticesData2[i+2]=0.0f;
//            circleVerticesData2[i+3] = 1.0f;
//            circleVerticesData2[i+4] = 1.0f;
//            circleVerticesData2[i+5] = 1.0f;
//            circleVerticesData2[i+6] = 1.0f;
//        }
//
//        circleVerticesData2[cnt-7] = circleVerticesData2[7];
//        circleVerticesData2[cnt-6] = circleVerticesData2[8];
//        circleVerticesData2[cnt-5] = circleVerticesData2[9];
//
//        circleVerticesData2[cnt-4] = 1.0f;
//        circleVerticesData2[cnt-3] = 1.0f;
//        circleVerticesData2[cnt-2] = 1.0f;
//        circleVerticesData2[cnt-1] = 1.0f;
//
//        mCircle1Vertices3 = ByteBuffer.allocateDirect(circleVerticesData2.length * mBytesPerFloat)
//                .order(ByteOrder.nativeOrder()).asFloatBuffer();
//        mCircle1Vertices3.put(circleVerticesData2).position(2);
//
//        final float[] circleVerticesData3 = new float[cnt];
//        circleVerticesData3[0] = 0.15f * 0.5f;
//        circleVerticesData3[1] = 0.21f * 0.31f;
//        circleVerticesData3[2] = 0.0f;
//
//        circleVerticesData3[3] = 0.0f;
//        circleVerticesData3[4] = 0.0f;
//        circleVerticesData3[5] = 0.0f;
//        circleVerticesData3[6] = 0.0f;
//
//        for (int i = 7; i < cnt-7; i+=7){
//            a = (float) ((float)(i-7) * Math.PI * 2.0f / (float)(cnt-14));
//            circleVerticesData3[i]= (float) (Math.cos(a)*0.5f * 0.07f + circleVerticesData3[0]);
//            circleVerticesData3[i+1]= (float) (Math.sin(a)*0.31f * 0.097f + circleVerticesData3[1]);
//            circleVerticesData3[i+2]=0.0f;
//            circleVerticesData3[i+3] = 0.0f;
//            circleVerticesData3[i+4] = 0.0f;
//            circleVerticesData3[i+5] = 0.0f;
//            circleVerticesData3[i+6] = 0.0f;
//        }
//
//        circleVerticesData3[cnt-7] = circleVerticesData3[7];
//        circleVerticesData3[cnt-6] = circleVerticesData3[8];
//        circleVerticesData3[cnt-5] = circleVerticesData3[9];
//
//        circleVerticesData3[cnt-4] = 0.0f;
//        circleVerticesData3[cnt-3] = 0.0f;
//        circleVerticesData3[cnt-2] = 0.0f;
//        circleVerticesData3[cnt-1] = 0.0f;
//
//        mCircle1Vertices4 = ByteBuffer.allocateDirect(circleVerticesData3.length * mBytesPerFloat)
//                .order(ByteOrder.nativeOrder()).asFloatBuffer();
//        mCircle1Vertices4.put(circleVerticesData3).position(3);
//
//        final float[] circleVerticesData4 = new float[cnt];
//        circleVerticesData4[0] = -0.15f * 0.5f;
//        circleVerticesData4[1] = 0.21f * 0.31f;
//        circleVerticesData4[2] = 0.0f;
//
//        circleVerticesData4[3] = 0.0f;
//        circleVerticesData4[4] = 0.0f;
//        circleVerticesData4[5] = 0.0f;
//        circleVerticesData4[6] = 0.0f;
//
//        for (int i = 7; i < cnt-7; i+=7){
//            a = (float) ((float)(i-7) * Math.PI * 2.0f / (float)(cnt-14));
//            circleVerticesData4[i]= (float) (Math.cos(a)*0.5f * 0.07f + circleVerticesData4[0]);
//            circleVerticesData4[i+1]= (float) (Math.sin(a)*0.31f * 0.097f + circleVerticesData4[1]);
//            circleVerticesData4[i+2]=0.0f;
//            circleVerticesData4[i+3] = 0.0f;
//            circleVerticesData4[i+4] = 0.0f;
//            circleVerticesData4[i+5] = 0.0f;
//            circleVerticesData4[i+6] = 0.0f;
//        }
//
//        circleVerticesData4[cnt-7] = circleVerticesData4[7];
//        circleVerticesData4[cnt-6] = circleVerticesData4[8];
//        circleVerticesData4[cnt-5] = circleVerticesData4[9];
//
//        circleVerticesData4[cnt-4] = 0.0f;
//        circleVerticesData4[cnt-3] = 0.0f;
//        circleVerticesData4[cnt-2] = 0.0f;
//        circleVerticesData4[cnt-1] = 0.0f;
//
//        mCircle1Vertices5 = ByteBuffer.allocateDirect(circleVerticesData4.length * mBytesPerFloat)
//                .order(ByteOrder.nativeOrder()).asFloatBuffer();
//        mCircle1Vertices5.put(circleVerticesData4).position(4);
//
//        final float[] circleVerticesData5 = new float[cnt];
//        circleVerticesData5[0] = 0.18f * 0.5f;
//        circleVerticesData5[1] = 0.26f * 0.31f;
//        circleVerticesData5[2] = 0.0f;
//
//        circleVerticesData5[3] = 1.0f;
//        circleVerticesData5[4] = 1.0f;
//        circleVerticesData5[5] = 1.0f;
//        circleVerticesData5[6] = 1.0f;
//
//        for (int i = 7; i < cnt-7; i+=7){
//            a = (float) ((float)(i-7) * Math.PI * 2.0f / (float)(cnt-14));
//            circleVerticesData5[i]= (float) (Math.cos(a)*0.5f * 0.035f + circleVerticesData5[0]);
//            circleVerticesData5[i+1]= (float) (Math.sin(a)*0.31f * 0.035f + circleVerticesData5[1]);
//            circleVerticesData5[i+2]=0.0f;
//            circleVerticesData5[i+3] = 1.0f;
//            circleVerticesData5[i+4] = 1.0f;
//            circleVerticesData5[i+5] = 1.0f;
//            circleVerticesData5[i+6] = 1.0f;
//        }
//
//        circleVerticesData5[cnt-7] = circleVerticesData5[7];
//        circleVerticesData5[cnt-6] = circleVerticesData5[8];
//        circleVerticesData5[cnt-5] = circleVerticesData5[9];
//
//        circleVerticesData5[cnt-4] = 1.0f;
//        circleVerticesData5[cnt-3] = 1.0f;
//        circleVerticesData5[cnt-5] = 1.0f;
//        circleVerticesData5[cnt-1] = 1.0f;
//
//        mCircle1Vertices6 = ByteBuffer.allocateDirect(circleVerticesData5.length * mBytesPerFloat)
//                .order(ByteOrder.nativeOrder()).asFloatBuffer();
//        mCircle1Vertices6.put(circleVerticesData5).position(5);
//
//        final float[] circleVerticesData6 = new float[cnt];
//        circleVerticesData6[0] = -0.12f * 0.5f;
//        circleVerticesData6[1] = 0.26f * 0.31f;
//        circleVerticesData6[2] = 0.0f;
//
//        circleVerticesData6[3] = 1.0f;
//        circleVerticesData6[4] = 1.0f;
//        circleVerticesData6[5] = 1.0f;
//        circleVerticesData6[6] = 1.0f;
//
//        for (int i = 7; i < cnt-7; i+=7){
//            a = (float) ((float)(i-7) * Math.PI * 2.0f / (float)(cnt-14));
//            circleVerticesData6[i]= (float) (Math.cos(a)*0.5f * 0.035f + circleVerticesData6[0]);
//            circleVerticesData6[i+1]= (float) (Math.sin(a)*0.31f * 0.035f + circleVerticesData6[1]);
//            circleVerticesData6[i+2]=0.0f;
//            circleVerticesData6[i+3] = 1.0f;
//            circleVerticesData6[i+4] = 1.0f;
//            circleVerticesData6[i+5] = 1.0f;
//            circleVerticesData6[i+6] = 1.0f;
//        }
//
//        circleVerticesData6[cnt-7] = circleVerticesData6[7];
//        circleVerticesData6[cnt-6] = circleVerticesData6[8];
//        circleVerticesData6[cnt-5] = circleVerticesData6[9];
//
//        circleVerticesData6[cnt-4] = 1.0f;
//        circleVerticesData6[cnt-3] = 1.0f;
//        circleVerticesData6[cnt-5] = 1.0f;
//        circleVerticesData6[cnt-1] = 1.0f;
//
//        mCircle1Vertices7 = ByteBuffer.allocateDirect(circleVerticesData6.length * mBytesPerFloat)
//                .order(ByteOrder.nativeOrder()).asFloatBuffer();
//        mCircle1Vertices7.put(circleVerticesData6).position(6);
//
//        final float[] triangle1VerticesData2 = {
//                // X, Y, Z,
//                // R, G, B, A
//                0.0f, -0.075f, 0.0f,
//                0.431f, 0.062f, 0.070f, 1.0f,
//
//                0.06f, -0.01f, 0.0f,
//                0.431f, 0.062f, 0.070f, 1.0f,
//
//                -0.06f, -0.01f, 0.0f,
//                0.431f, 0.062f, 0.070f, 1.0f
//
//        };
//
//        mTriangle1Vertices2 = ByteBuffer.allocateDirect(triangle1VerticesData2.length * mBytesPerFloat)
//                .order(ByteOrder.nativeOrder()).asFloatBuffer();
//        mTriangle1Vertices2.put(triangle1VerticesData2).position(7);
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config)
    {glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        final String vertexShader =

                "attribute vec4 a_Position;     \n"     // Per-vertex position information we will pass in.
                        + "attribute vec4 a_Color;        \n"     // Per-vertex color information we will pass in.

                        + "varying vec4 v_Color;          \n"     // This will be passed into the fragment shader.

                        + "void main()                    \n"     // The entry point for our vertex shader.
                        + "{                              \n"
                        + "   v_Color = a_Color;          \n"     // Pass the color through to the fragment shader.
                        // It will be interpolated across the triangle.
                        + "   gl_Position =  a_Position; \n"  // gl_Position is a special variable used to store the final position.

                        + "}                              \n";    // normalized screen coordinates.

        final String fragmentShader =
                "precision mediump float;       \n"       // Set the default precision to medium. We don't need as high of a
                        // precision in the fragment shader.
                        + "varying vec4 v_Color;          \n"     // This is the color from the vertex shader interpolated across the
                        // triangle per fragment.
                        + "void main()                    \n"     // The entry point for our fragment shader.
                        + "{                              \n"
                        + "   gl_FragColor = v_Color;     \n"     // Pass the color directly through the pipeline.
                        + "}                              \n";

        // Load in the vertex shader.
        int vertexShaderHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);

        if (vertexShaderHandle != 0)
        {
            // Pass in the shader source.
            GLES20.glShaderSource(vertexShaderHandle, vertexShader);

            // Compile the shader.
            GLES20.glCompileShader(vertexShaderHandle);

            // Get the compilation status.
            final int[] compileStatus = new int[1];
            GLES20.glGetShaderiv(vertexShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

            // If the compilation failed, delete the shader.
            if (compileStatus[0] == 0)
            {
                GLES20.glDeleteShader(vertexShaderHandle);
                vertexShaderHandle = 0;
            }
        }

        if (vertexShaderHandle == 0)
        {
            throw new RuntimeException("Error creating vertex shader.");
        }

        // Load in the fragment shader shader.
        int fragmentShaderHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);

        if (fragmentShaderHandle != 0)
        {
            // Pass in the shader source.
            GLES20.glShaderSource(fragmentShaderHandle, fragmentShader);

            // Compile the shader.
            GLES20.glCompileShader(fragmentShaderHandle);

            // Get the compilation status.
            final int[] compileStatus = new int[1];
            GLES20.glGetShaderiv(fragmentShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

            // If the compilation failed, delete the shader.
            if (compileStatus[0] == 0)
            {
                GLES20.glDeleteShader(fragmentShaderHandle);
                fragmentShaderHandle = 0;
            }
        }

        if (fragmentShaderHandle == 0)
        {
            throw new RuntimeException("Error creating fragment shader.");
        }

        // Create a program object and store the handle to it.
        int programHandle = GLES20.glCreateProgram();

        if (programHandle != 0)
        {
            // Bind the vertex shader to the program.
            GLES20.glAttachShader(programHandle, vertexShaderHandle);

            // Bind the fragment shader to the program.
            GLES20.glAttachShader(programHandle, fragmentShaderHandle);

            // Bind attributes
            GLES20.glBindAttribLocation(programHandle, 0, "a_Position");
            GLES20.glBindAttribLocation(programHandle, 1, "a_Color");

            // Link the two shaders together into a program.
            GLES20.glLinkProgram(programHandle);

            // Get the link status.
            final int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(programHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);

            // If the link failed, delete the program.
            if (linkStatus[0] == 0)
            {
                GLES20.glDeleteProgram(programHandle);
                programHandle = 0;
            }
        }

        if (programHandle == 0)
        {
            throw new RuntimeException("Error creating program.");
        }

        mPositionHandle = GLES20.glGetAttribLocation(programHandle, "a_Position");
        mColorHandle = GLES20.glGetAttribLocation(programHandle, "a_Color");

        // Tell OpenGL to use this program when rendering.
        GLES20.glUseProgram(programHandle);

    }

    @Override
    // Set the OpenGL viewport to fill the entire surface.
    public void onSurfaceChanged(GL10 glUnused, int width, int height) {
        glViewport(0, 0, width, height);

    }

    @Override
    // Clear the rendering surface.
    public void onDrawFrame(GL10 glUnused) {
        //   glClear(GL_COLOR_BUFFER_BIT);
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        // Draw the triangle facing straight on.
        //Matrix.setIdentityM(mModelMatrix, 0);
        // Matrix.translateM(mModelMatrix, 0, x, 0.0f, 0.0f);
        drawTriangle(mTriangle1Vertices1);
//        drawCircle(mCircle1Vertices1);
//        drawCircle(mCircle1Vertices2);
//        drawCircle(mCircle1Vertices3);
//        drawCircle(mCircle1Vertices4);
//        drawCircle(mCircle1Vertices5);
//        drawCircle(mCircle1Vertices6);
//        drawCircle(mCircle1Vertices7);
//        drawTriangle2(mTriangle1Vertices2);
    }
    /**
     * Draws a triangle from the given vertex data.
     *
     * @param aTriangleBuffer The buffer containing the vertex data.
     */
    private void drawTriangle(final FloatBuffer aTriangleBuffer)
    {
        // Pass in the position information
        aTriangleBuffer.position(mPositionOffset);
        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
                mStrideBytes, aTriangleBuffer);

        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Pass in the color information
        aTriangleBuffer.position(mColorOffset);
        GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,
                mStrideBytes, aTriangleBuffer);

        GLES20.glEnableVertexAttribArray(mColorHandle);

            /* This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
            // (which currently contains model * view).
            Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);

            // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
            // (which now contains model * view * projection).
            Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0); */

        // GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

    }

//    private void drawTriangle2(final FloatBuffer aTriangleBuffer)
//    {
//        // Pass in the position information
//        aTriangleBuffer.position(mPositionOffset);
//        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
//                mStrideBytes, aTriangleBuffer);
//
//        GLES20.glEnableVertexAttribArray(mPositionHandle);
//
//        // Pass in the color information
//        aTriangleBuffer.position(mColorOffset);
//        GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,
//                mStrideBytes, aTriangleBuffer);
//
//        GLES20.glEnableVertexAttribArray(mColorHandle);
//
//            /* This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
//            // (which currently contains model * view).
//            Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
//
//            // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
//            // (which now contains model * view * projection).
//            Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0); */
//
//        // GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
//        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 3);
//
//    }
//
//    private void drawCircle(final FloatBuffer aCircleBuffer)
//    {
//        // Pass in the position information
//        aCircleBuffer.position(mPositionOffset);
//        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
//                mStrideBytes, aCircleBuffer);
//
//        GLES20.glEnableVertexAttribArray(mPositionHandle);
//
//        // Pass in the color information
//        aCircleBuffer.position(mColorOffset);
//        GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,
//                mStrideBytes, aCircleBuffer);
//
//        GLES20.glEnableVertexAttribArray(mColorHandle);
//
//            /* This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
//            // (which currently contains model * view).
//            Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
//
//            // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
//            // (which now contains model * view * projection).
//            Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0); */
//
//        // GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
//        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 50);
//
//    }
}

