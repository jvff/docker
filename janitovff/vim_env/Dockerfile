FROM janitovff/vimspf13

RUN git config --global user.name "Janito Vaqueiro Ferreira Filho"
RUN git config --global user.email "janito.vff@gmail.com"

COPY ssh_config /root/.ssh/config

RUN sed -i -e '/let g:airline_powerline_fonts = 1/ s/^\( *\)"/\1/' /root/.vimrc.before
RUN sed -i -e 's/set virtualedit=onemore/"&/' /root/.vimrc
RUN sed -i -e 's/set iskeyword-=\./"&/' /root/.vimrc

RUN echo "Bundle 'tpope/vim-dispatch'" >> /root/.vimrc.bundles.local
RUN echo "Bundle 'tpope/vim-unimpaired'" >> /root/.vimrc.bundles.local
RUN echo "Bundle 'junegunn/gv.vim'" >> /root/.vimrc.bundles.local
RUN vim +PluginInstall +qall

RUN echo "set cc=81" >> /root/.vimrc.local
RUN echo "noremap <Leader>mk :Make<CR>" >> /root/.vimrc.local
RUN echo "noremap <Leader>gf :Git fetch<CR>" >> /root/.vimrc.local
RUN echo "noremap <Leader>gl :GV --all<CR>" >> /root/.vimrc.local
