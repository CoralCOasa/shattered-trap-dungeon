/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredtrap.shatteredpixeldungeon.scenes;

import com.shatteredtrap.shatteredpixeldungeon.SPDSettings;
import com.shatteredtrap.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredtrap.shatteredpixeldungeon.effects.Flare;
import com.shatteredtrap.shatteredpixeldungeon.items.spells.ReclaimTrap;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredtrap.shatteredpixeldungeon.ui.Archs;
import com.shatteredtrap.shatteredpixeldungeon.ui.ExitButton;
import com.shatteredtrap.shatteredpixeldungeon.ui.Icons;
import com.shatteredtrap.shatteredpixeldungeon.ui.RenderedTextMultiline;
import com.shatteredtrap.shatteredpixeldungeon.ui.Window;
import com.watabou.input.Touchscreen.Touch;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Image;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.TouchArea;
import com.watabou.utils.DeviceCompat;

public class AboutScene extends PixelScene {

	private static final String TTL_SHPX = "Shattered Pixel Dungeon";

	private static final String TXT_SHPX =
			"Design, Code, & Graphics: Evan";

	private static final String LNK_SHPX = "ShatteredPixel.com";

	private static final String TTL_SHPX2 = "Shattered Trap Dungeon";

	private static final String TXT_SHPX2 =
			"Created by a random PD fan.";

	private static final String LNK_SHPX2 = "github.com/CoralCOasa/shattered-trap-dungeon";

	private static final String TTL_WATA = "Pixel Dungeon";

	private static final String TXT_WATA =
			"Code & Graphics: Watabou\n" +
			"Music: Cube_Code";

	private static final String LNK_WATA = "pixeldungeon.watabou.ru";

	@Override
	public void create() {
		super.create();

		final float colWidth = Camera.main.width / (SPDSettings.landscape() ? 2 : 1);
		final float colTop = (Camera.main.height / 2) - (SPDSettings.landscape() ? 70 : 100);
		final float wataOffset = SPDSettings.landscape() ? colWidth : 0;








		Image shpx2 = new ItemSprite(ItemSpriteSheet.ELIXIR_TRAPSKILL);
		shpx2.x = (colWidth - shpx2.width()) / 2;
		//shpx2.y = wataLink.top() + wata.height + 10;
		shpx2.y = colTop;

		align(shpx2);
		add( shpx2 );

		new Flare( 7, 64 ).color( 5710159, true ).show( shpx2, 0 ).angularSpeed = +20;

		RenderedText shpxtitle2 = renderText( TTL_SHPX2, 8 );
		shpxtitle2.hardlight( 11497922 );
		add( shpxtitle2 );

		shpxtitle2.x = (colWidth - shpxtitle2.width()) / 2;
		shpxtitle2.y = shpx2.y + shpx2.height + 2.5f;
		align(shpxtitle2);

		RenderedTextMultiline shpxtext2 = renderMultiline( TXT_SHPX2, 8 );
		shpxtext2.maxWidth((int)Math.min(colWidth, 120));
		add( shpxtext2 );

		shpxtext2.setPos((colWidth - shpxtext2.width()) / 2, shpxtitle2.y + shpxtitle2.height() + 6);
		align(shpxtext2);

		RenderedTextMultiline shpxlink2 = renderMultiline( LNK_SHPX2, 8 );
		shpxlink2.maxWidth(shpxtext2.maxWidth());
		shpxlink2.hardlight( 11497922 );
		add( shpxlink2 );

		shpxlink2.setPos((colWidth - shpxlink2.width()) / 2, shpxtext2.bottom() + 3);
		align(shpxlink2);

		TouchArea shpxhotArea2 = new TouchArea( shpxlink2.left(), shpxlink2.top(), shpxlink2.width(), shpxlink2.height() ) {
			@Override
			protected void onClick( Touch touch ) {
				DeviceCompat.openURI( "https://" + LNK_SHPX2 );
			}
		};
		add( shpxhotArea2 );









		Image shpx = Icons.SHPX.get();
		shpx.x = (colWidth - shpx.width()) / 2;
		shpx.y = shpxlink2.top() + shpx.height + 10;
		align(shpx);
		add( shpx );

		new Flare( 7, 64 ).color( 0x225511, true ).show( shpx, 0 ).angularSpeed = +20;

		RenderedText shpxtitle = renderText( TTL_SHPX, 8 );
		shpxtitle.hardlight( Window.SHPX_COLOR );
		add( shpxtitle );

		shpxtitle.x = (colWidth - shpxtitle.width()) / 2;
		shpxtitle.y = shpx.y + shpx.height + 2.5f;
		align(shpxtitle);

		RenderedTextMultiline shpxtext = renderMultiline( TXT_SHPX, 8 );
		shpxtext.maxWidth((int)Math.min(colWidth, 120));
		add( shpxtext );

		shpxtext.setPos((colWidth - shpxtext.width()) / 2, shpxtitle.y + shpxtitle.height() + 6);
		align(shpxtext);

		RenderedTextMultiline shpxlink = renderMultiline( LNK_SHPX, 8 );
		shpxlink.maxWidth(shpxtext.maxWidth());
		shpxlink.hardlight( Window.SHPX_COLOR );
		add( shpxlink );

		shpxlink.setPos((colWidth - shpxlink.width()) / 2, shpxtext.bottom() + 3);
		align(shpxlink);

		TouchArea shpxhotArea = new TouchArea( shpxlink.left(), shpxlink.top(), shpxlink.width(), shpxlink.height() ) {
			@Override
			protected void onClick( Touch touch ) {
				DeviceCompat.openURI( "https://" + LNK_SHPX );
			}
		};
		add( shpxhotArea );

		Image wata = Icons.WATA.get();
		wata.x = wataOffset + (colWidth - wata.width()) / 2;
		wata.y = SPDSettings.landscape() ?
						colTop:
						shpxlink.top() + wata.height + 10;
		align(wata);
		add( wata );

		new Flare( 7, 64 ).color( 0x112233, true ).show( wata, 0 ).angularSpeed = +20;

		RenderedText wataTitle = renderText( TTL_WATA, 8 );
		wataTitle.hardlight(Window.TITLE_COLOR);
		add( wataTitle );

		wataTitle.x = wataOffset + (colWidth - wataTitle.width()) / 2;
		wataTitle.y = wata.y + wata.height + 5.5f;
		align(wataTitle);

		RenderedTextMultiline wataText = renderMultiline( TXT_WATA, 8 );
		wataText.maxWidth((int)Math.min(colWidth, 120));
		add( wataText );

		wataText.setPos(wataOffset + (colWidth - wataText.width()) / 2, wataTitle.y + wataTitle.height() + 6);
		align(wataText);

		RenderedTextMultiline wataLink = renderMultiline( LNK_WATA, 8 );
		wataLink.maxWidth((int)Math.min(colWidth, 120));
		wataLink.hardlight(Window.TITLE_COLOR);
		add(wataLink);

		wataLink.setPos(wataOffset + (colWidth - wataLink.width()) / 2 , wataText.bottom() + 3);
		align(wataLink);

		TouchArea hotArea = new TouchArea( wataLink.left(), wataLink.top(), wataLink.width(), wataLink.height() ) {
			@Override
			protected void onClick( Touch touch ) {
				DeviceCompat.openURI( "https://" + LNK_WATA );
			}
		};
		add( hotArea );








		Archs archs = new Archs();
		archs.setSize( Camera.main.width, Camera.main.height );
		addToBack( archs );

		ExitButton btnExit = new ExitButton();
		btnExit.setPos( Camera.main.width - btnExit.width(), 0 );
		add( btnExit );

		fadeIn();
	}

	@Override
	protected void onBackPressed() {
		ShatteredPixelDungeon.switchNoFade(TitleScene.class);
	}
}
