/*
 * Copyright (C) 2015 Bilibili
 * Copyright (C) 2015 Zhang Rui <bbcallen@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jinyx.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AndroidMediaController extends IJKMediaController implements IMediaController {

    private View mTitleBar;

    public AndroidMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AndroidMediaController(Context context) {
        super(context);
    }

    public void setSupportActionBar(View titleBar) {
        mTitleBar = titleBar;
        if (titleBar != null) {
            if (isShowing()) {
                titleBar.setVisibility(View.VISIBLE);
            } else {
                titleBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void show() {
        super.show();
        if (mTitleBar != null) {
            mTitleBar.setVisibility(VISIBLE);
        }
    }

    // TODO show hide 的 回调不是 5 秒处理逻辑
    @Override
    public void hide() {
        super.hide();
        if (mTitleBar != null) {
            mTitleBar.setVisibility(GONE);
        }
        for (View view : mShowOnceArray) {
            view.setVisibility(View.GONE);
        }
        mShowOnceArray.clear();
    }

    //----------
    // Extends
    //----------
    private ArrayList<View> mShowOnceArray = new ArrayList<View>();

    public void showOnce(@NonNull View view) {
        mShowOnceArray.add(view);
        view.setVisibility(View.VISIBLE);
        show();
    }
}
